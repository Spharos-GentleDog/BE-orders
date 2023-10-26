package egenius.orders.domain.payment.application;

import egenius.orders.domain.payment.dto.PaymentRequestDto;
import egenius.orders.domain.payment.entity.Payment;
import egenius.orders.domain.payment.infrastructure.*;
import egenius.orders.global.common.exception.BaseException;
import egenius.orders.global.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final CancelsRepository cancelsRepository;
    private final ModelMapper modelMapper;

    /**
     * payment
     *
     * 1. 결제 요청
     * 2. 결제 중복 체크
     */

    // 1. 결제 요청
    @Override
    public void paymentRequest(PaymentRequestDto requestDto) {
        // 중복체크 결과가 true라면 계속 진행
        checkPaymentDupl(requestDto.getPaymentKey());
        // modelMapper를 사용하여 entity 생성 후 저장
        Payment payment = modelMapper.map(requestDto, Payment.class);
        paymentRepository.save(payment);
    }

    // 2. 결제 키로 중복 조회
    @Override
    public boolean checkPaymentDupl(String paymentKey) {
        Optional<Payment> payment = paymentRepository.findByPaymentKey(paymentKey);
        // paymentKey로 조회, 조회된다면 중복된다는 뜻이므로 ALREADY_PAID_ORDER_ID 에러 throw
        if (payment.isPresent()) {
            throw new BaseException(BaseResponseStatus.ALREADY_PAID_ORDER_ID);
        }
        // 존재하지 않는다면 true를 return
        return true;
    }
}
