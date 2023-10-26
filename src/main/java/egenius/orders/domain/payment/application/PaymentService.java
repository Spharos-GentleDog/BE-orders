package egenius.orders.domain.payment.application;

import egenius.orders.domain.payment.dto.CancelsRequestDto;
import egenius.orders.domain.payment.dto.PaymentRequestDto;
import egenius.orders.domain.payment.entity.Payment;

public interface PaymentService {

    /**
     * payment
     *
     * 1. 결제 요청
     * 2. 결제 키로 조회
     * 3. 결제 취소
     */

    // 1. 결제 요청
    void paymentRequest(PaymentRequestDto requestDto);

    // 2. 결제 키로 조회
    Payment getPaymentByKey(String paymentKey);

    // 3. 결제 취소
    void paymentCancel(CancelsRequestDto requestDto);

}
