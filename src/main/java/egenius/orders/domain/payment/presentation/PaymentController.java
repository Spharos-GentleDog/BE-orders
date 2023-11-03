package egenius.orders.domain.payment.presentation;

import com.google.common.util.concurrent.ListenableFuture;
import egenius.orders.domain.payment.application.PaymentServiceImpl;
import egenius.orders.domain.payment.dto.CancelsRequestDto;
import egenius.orders.domain.payment.dto.PaymentRequestDto;
import egenius.orders.domain.payment.webdto.CancelsInWebDto;
import egenius.orders.domain.payment.webdto.PaymentInWebDto;
import egenius.orders.global.common.response.BaseResponse;
import egenius.orders.global.config.kafka.KafkaAdminConfig;
import egenius.orders.global.config.kafka.KafkaProducerConfig;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentServiceImpl paymentService;
    private final ModelMapper modelMapper;

    // producer에서 message를 send 하기위한 카프카 템플릿
    private final KafkaTemplate<String, String> kafkaTemplate;
    // 미리 bean 등록해둔 topic
    private final NewTopic paymentTopic;
    private final KafkaAdminConfig kafkaAdminConfig;
    private final KafkaProducerConfig producerConfig;

    /**
     * payment
     * 1. 결제
     * 2. 결제 취소
     */

    //1. 결제
    @Operation(summary = "결제", description = "결제내역 저장", tags = {"Orders Payment"})
    @PostMapping("")
    public BaseResponse<?> requestPayment(@RequestBody PaymentInWebDto inDto) {
        PaymentRequestDto requestDto = modelMapper.map(inDto, PaymentRequestDto.class);
        paymentService.paymentRequest(requestDto);
        return new BaseResponse<>();
    }

    //2. 결제 취소
    @Operation(summary = "결제 취소", description = "결제 취소", tags = {"Orders Payment"})
    @PostMapping("/cancel")
    public BaseResponse<?> cancelPayment(@RequestBody CancelsInWebDto inDto) {
        CancelsRequestDto requestDto = modelMapper.map(inDto, CancelsRequestDto.class);
        paymentService.paymentCancel(requestDto);
        return new BaseResponse<>();
    }

    //3
    @GetMapping("/producer")
    public void producerTest() {
        String message = "1";
        int i = 0;
        while (i < 10) {
            // message를 설정하고 kafkaTemplate.send(토픽이름,메시지)로 메시지를 전달
            kafkaTemplate.send(paymentTopic.name(), message);
            i++;
        }

        kafkaAdminConfig.addPartitionsToPaymentTopic();
    }
}
