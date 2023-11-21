package egenius.orders.domain.batch.jobs;

import com.querydsl.core.Tuple;
import egenius.orders.domain.batch.chunk.QuerydslPagingItemReader;
import egenius.orders.domain.payment.entity.enums.PaymentMethod;
import egenius.orders.domain.payment.entity.QPayment;
import egenius.orders.domain.payment.entity.QProductPayment;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PaymentTransferJob {

    // spring batch
    private final static int CHUNK_SIZE = 100;
    private final JobRepository jobRepository;
    private final EntityManagerFactory enf;
    private final PlatformTransactionManager transactionManager;
    // kafka
    private final KafkaTemplate<String, Map> paymentTransferTemplate;  // producer에서 message를 send 하기위한 카프카 템플릿
    private final NewTopic paymentTopic; // 미리 bean 등록해둔 topic



    /**
     * 1. job
     * 2. step
     * 3. reader
     * 4. processor
     * 5. writer
     */

    // 1. job
    @Bean
    public Job paymentJob() {
        return new JobBuilder("paymentTransferJob", jobRepository)
                .start(paymentTransferStep(jobRepository))
                .build();
    }

    // 2. step
    @Bean
    public Step paymentTransferStep(JobRepository jobRepository) {
        return new StepBuilder("paymentTransferStep", jobRepository)
                // chunk의 제네릭은 <reader에서 반환하는값, processor에서 반환하는값>을 적으면 됨
                // processor를 설정하지 않았다면, writer에서 쓰는 타입을 적으면 됨
                .<Tuple,Map>chunk(CHUNK_SIZE, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }


    // 3. reader -> QueryDsl은 Tuple형태로 데이터를 return하므로 제네릭에 tuple을 적는다
    // 결제 1건당 ->
    // 상품별 -> 결제금액, 결제수단, 상품명, 상품코드, 상품 메인이미지 url, 승인날짜, 판매자email,
    // todo: noOffset으로 성능향상 시켜보기
    // todo: order 도메인이 완성되면, paymentKey에 해당하는 order를 조회하고 vendorEmail를 넣기
    @Bean
    public QuerydslPagingItemReader<Tuple> reader() {
        // 자정이 넘어서 실행되므로 -> '시작 날짜' = '어제', '끝 날짜' = '오늘' 이 된다
        LocalDateTime start = LocalDate.now().minusDays(1).atStartOfDay();
        LocalDateTime end = LocalDate.now().atStartOfDay();

        // 오늘의 상품별 결제 내역을 모두 가져옴
        QPayment qPayment = QPayment.payment;
        QProductPayment qProductPayment = QProductPayment.productPayment;
        QuerydslPagingItemReader<Tuple> reader = new QuerydslPagingItemReader<>(
                enf,
                CHUNK_SIZE,
                queryFactory ->
                        queryFactory
                        .select(
                                qPayment.paymentMethod,
                                qPayment.approvedAt,
                                qProductPayment.vendorEmail,
                                qProductPayment.productName,
                                qProductPayment.productCode,
                                qProductPayment.productMainImageUrl,
                                qProductPayment.productAmount,
                                qProductPayment.count,
                                qPayment.paymentKey
                                )
                        .from(qPayment)
                        .join(qPayment.productPaymentList, qProductPayment)
                        .where(
                                qPayment.approvedAt
                                        .goe(start)
                                        .and(qPayment.approvedAt.lt(end)))
        );
        System.out.println("reader = " + reader);
        // PageSize가 10이고, ChunkSize가 50이라면 ItemReader에서 Page 조회가 5번 일어나면 1번 의 트랜잭션이 발생하여 Chunk가 처리됩니다.
        // 한번의 트랜잭션 처리를 위해 5번의 쿼리 조회가 발생하기 때문에 성능상 이슈가 발생할 수 있습니다.
        // 또한 PageSize와 ChunkSize가 다른경우 JPA를 사용하면 영속성 컨텍스트가 깨질 수도 있음 -> 따라서 두 개를 같게 해야한다
        reader.setPageSize(CHUNK_SIZE);
        return reader;
    }


    // 4. processor
    @Bean
    public ItemProcessor<Tuple, Map> processor() {
        return tuple -> {
            Map<String, String> jsonData = new HashMap<>();
            // key-value 삽입
            jsonData.put("paymentMethod", String.valueOf(tuple.get(0, PaymentMethod.class)));
            jsonData.put("paidAt", tuple.get(
                    1,
                    LocalDateTime.class).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            jsonData.put("vendorEmail", tuple.get(2, String.class));
            jsonData.put("productName", tuple.get(3, String.class));
            jsonData.put("productCode", tuple.get(4, String.class));
            jsonData.put("productMainImageUrl", tuple.get(5, String.class));
            jsonData.put("productAmount", String.valueOf(tuple.get(6, Integer.class)));
            jsonData.put("count", String.valueOf(tuple.get(7, Integer.class)));
            jsonData.put("key", tuple.get(8, String.class));
            System.out.println("jsonData = " + jsonData);
            return jsonData;
        };
    }


    // 5. writer
    @Bean
    public ItemWriter<Map> writer() {
        log.info("payment history send start!");
//        // chunk단위로 한번에 보낸다
//        return chunk ->
//            kafkaTemplate.send(paymentTopic.name(), chunk);
        // chunk에서 item을 하나씩 뽑아서 전달한다 -> consumer쪽에서
        return chunk ->
                chunk.forEach(item->{
                    System.out.println("item = " + item);
                    paymentTransferTemplate.send(paymentTopic.name(), null, item);
                });
    }
}
