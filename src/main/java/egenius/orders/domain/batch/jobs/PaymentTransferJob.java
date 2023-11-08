package egenius.orders.domain.batch.jobs;

import com.querydsl.core.Tuple;
import egenius.orders.domain.batch.entity.PaymentTransferHistory;
import egenius.orders.domain.batch.infrastructure.PaymentTransferHistoryRepository;
import egenius.orders.domain.batch.chunk.QuerydslPagingItemReader;
import egenius.orders.domain.payment.entity.PaymentMethod;
import egenius.orders.domain.payment.entity.QPayment;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
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

    // util
    private final ModelMapper modelMapper;
    // repository
    private final PaymentTransferHistoryRepository historyRepository;

    // spring batch
    private final static int CHUNK_SIZE = 100;
    private final JobRepository jobRepository;
    private final EntityManagerFactory enf;
    private final PlatformTransactionManager transactionManager;
    // kafka
    private final KafkaTemplate<String, Map> kafkaTemplate;  // producer에서 message를 send 하기위한 카프카 템플릿
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
    @Bean
    public QuerydslPagingItemReader<Tuple> reader() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().plusDays(1).atStartOfDay();
        System.out.println("end = " + end);

        // 오늘의 결제 내역, 주문내역을 모두 가져옴
        QPayment qPayment = QPayment.payment;
        return new QuerydslPagingItemReader<>(enf, CHUNK_SIZE,
                queryFactory -> queryFactory
                        .select(qPayment.paymentTotalAmount,
                                qPayment.paymentMethod,
                                qPayment.approvedAt)
                        .from(qPayment)
                        .where(qPayment.approvedAt.goe(start).and(qPayment.approvedAt.lt(end)))
        );
    }

    // 4. processor
    @Bean
    public ItemProcessor<Tuple, Map> processor() {
        return tuple -> {
            Map<String, String> jsonData = new HashMap<>();
            // 형 변환
            Integer amount = tuple.get(0, Integer.class);
            PaymentMethod method = tuple.get(1, PaymentMethod.class);
            LocalDateTime date = tuple.get(2, LocalDateTime.class);
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

            // key-value 삽입
            jsonData.put("paymentAmount", String.valueOf(amount));
            jsonData.put("paymentMethod", String.valueOf(method));
            jsonData.put("paidAt", formattedDate);
            jsonData.put("vendorEmail", "test@email.com");
            return jsonData;
        };
    }


    // 5. writer
    @Bean
    public ItemWriter<Map> writer() {
        System.out.println("start!");
        return chunk -> {
            log.info("kafka - payment send 시작");
            // kafka producer를 사용해서 보내기 시작
            System.out.println("chunk = " + chunk);
            chunk.forEach(data -> {
                log.info("data = "+data);
                System.out.println(data.getClass().getName());
                PaymentTransferHistory history = modelMapper.map(data, PaymentTransferHistory.class);
                historyRepository.save(history);
                kafkaTemplate.send(paymentTopic.name(), data);
                }
            );
        };
    }
}
