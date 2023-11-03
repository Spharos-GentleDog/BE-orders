package egenius.orders.domain.payment.application;

import egenius.orders.domain.payment.dto.CancelsRequestDto;
import egenius.orders.domain.payment.dto.PaymentRequestDto;
import egenius.orders.domain.payment.entity.Cancels;
import egenius.orders.domain.payment.entity.Payment;
import egenius.orders.domain.payment.entity.PaymentStatus;
import egenius.orders.domain.payment.infrastructure.*;
import egenius.orders.global.common.exception.BaseException;
import egenius.orders.global.common.response.BaseResponseStatus;
//import egenius.orders.global.config.kafka.KafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CancelsRepository cancelsRepository;
    private final ModelMapper modelMapper;
//    private final KafkaConfig kafkaConfig;


    /**
     * payment
     * <p>
     * 1. 결제 요청
     * 2. 결제 키로 조회
     * 3. 결제 취소
     */

    // 1. 결제 요청
    @Override
    public void paymentRequest(PaymentRequestDto requestDto) {
        // 조회 결과가 존재한다면 중복된다는 의미이므로, ALREADY_PAID_ORDER_ID EXCEPTION을 return
        Payment searchResult = getPaymentByKey(requestDto.getPaymentKey());
        if (!(searchResult == null)) {
            throw new BaseException(BaseResponseStatus.ALREADY_PAID_ORDER_ID);
        }
        // modelMapper를 사용하여 entity 생성 후 저장
        Payment payment = modelMapper.map(requestDto, Payment.class);
        paymentRepository.save(payment);
    }

    // 2. 결제 키로 조회
    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentByKey(String paymentKey) {
        Optional<Payment> payment = paymentRepository.findByPaymentKey(paymentKey);
        // paymentKey로 조회
        if (payment.isPresent()) {
            return payment.get();
        }
        // 존재하지 않는다면 null을 return
        return null;
    }

    // 3. 결제 취소
    @Override
    public void paymentCancel(CancelsRequestDto requestDto) {
        // paymentKey로 기존 결제를 조회 및 상태 업데이트
        Payment payment = getPaymentByKey(requestDto.getPaymentKey());
        // 조회가 존재하지 않는다면, 에러를 return
        if (payment == null) {
            throw new BaseException(BaseResponseStatus.DOSE_NOT_EXIST_PAYMENT);
        }
        // 기존 payment 내역을 update (취소 가능 잔고->결제취소 금액만큼 제외, 결제 상태-> 결제취소로 변경)
        payment.updateBalanceAmount(requestDto.getCancelAmount());
        payment.updateStatus(PaymentStatus.CANCELED);

        // 취소 객체 생성
        Cancels cancels = modelMapper.map(requestDto, Cancels.class);
        cancels = cancels.toBuilder().payment(payment).build();
        // 저장
        cancelsRepository.save(cancels);
    }


//    @KafkaListener(topics = "payment_data", groupId = "test1")
//    public void listen(ConsumerRecord<String, String> record) {
//        String topic = record.topic();
//        int partition = record.partition();
//        long offset = record.offset();
//        String key = record.key();
//        String value = record.value();
//
//        // Kafka 메시지 처리 로직을 여기에 구현
//        System.out.println("kafka message value:" + value);
//    }

//    public void continueListen() {
//        log.info("ContinueListen is running");
//
//        KafkaConsumer<String, String> consumer = kafkaConfig.createConsumer();
//        consumer.subscribe(Arrays.asList("payment_data"));
//
//        int result = 0;
//        while (true) {
//            // poll에서 설정한 ms동안 데이터를 기다린다 = 설정한 값동안 기다리다가 다음 코드를 실행한다
//            // 만약 그 시간동안 데이터가 오지 않는다면 -> 빈 records를 반환, 데이터가 있다면 데이터 records를 반환
//            ConsumerRecords<String, String> records = consumer.poll(10000000);
//            // records는 데이터 list이므로, record로 뽑아낸 후 record.value()로 프로듀서가 보낸 진짜 값을 가져올 수 있다
//            for (ConsumerRecord<String, String> record : records) {
//                result += Integer.parseInt(record.value());
//                System.out.println("record.value() = " + record.value()+", result = "+result);
//
//            }
//            System.out.println("final result = " + result);
//        }
//    }
}
