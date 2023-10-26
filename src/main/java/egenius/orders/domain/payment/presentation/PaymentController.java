package egenius.orders.domain.payment.presentation;

import egenius.orders.domain.payment.application.PaymentServiceImpl;
import egenius.orders.domain.payment.dto.PaymentRequestDto;
import egenius.orders.domain.payment.webdto.PaymentInWebDto;
import egenius.orders.global.common.exception.BaseException;
import egenius.orders.global.common.response.BaseResponse;
import egenius.orders.global.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentServiceImpl paymentService;
    private final ModelMapper modelMapper;

    /**
     * payment
     * 1. 카드 번호 결제
     */

    //1. 카드번호 결제
    @PostMapping("")
    public BaseResponse<?> cardPay(@RequestBody PaymentInWebDto inDto) {
        PaymentRequestDto requestDto = modelMapper.map(inDto, PaymentRequestDto.class);
        paymentService.paymentRequest(requestDto);
        return new BaseResponse<>();
    }


}
