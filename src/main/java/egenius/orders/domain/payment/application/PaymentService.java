package egenius.orders.domain.payment.application;

import egenius.orders.domain.payment.dto.PaymentRequestDto;
import egenius.orders.global.common.exception.BaseException;

public interface PaymentService {

    /**
     * payment
     *
     * 1. 결제 요청
     * 2. 결제 중복 체크
     */

    // 1. 결제 요청
    void paymentRequest(PaymentRequestDto requestDto) throws BaseException;

    // 2. 결제 키로 중복 조회
    boolean checkPaymentDupl(String paymentKey) throws BaseException;
}
