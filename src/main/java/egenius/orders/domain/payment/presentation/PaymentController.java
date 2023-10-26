package egenius.orders.domain.payment.presentation;

import egenius.orders.domain.payment.application.PaymentServiceImpl;
import egenius.orders.domain.payment.dto.CancelsRequestDto;
import egenius.orders.domain.payment.dto.PaymentRequestDto;
import egenius.orders.domain.payment.webdto.CancelsInWebDto;
import egenius.orders.domain.payment.webdto.PaymentInWebDto;
import egenius.orders.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
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
     * 1. 결제
     * 2. 결제 취소
     */

    //1. 결제
    @Operation(summary = "결제", description = "결제내역 저장", tags = { "Orders Payment" })
    @PostMapping("")
    public BaseResponse<?> requestPayment(@RequestBody PaymentInWebDto inDto) {
        PaymentRequestDto requestDto = modelMapper.map(inDto, PaymentRequestDto.class);
        paymentService.paymentRequest(requestDto);
        return new BaseResponse<>();
    }

    //2. 결제 취소
    @Operation(summary = "결제 취소", description = "결제 취소", tags = { "Orders Payment" })
    @PostMapping("/cancel")
    public BaseResponse<?> cancelPayment(@RequestBody CancelsInWebDto inDto) {
        CancelsRequestDto requestDto = modelMapper.map(inDto, CancelsRequestDto.class);
        paymentService.paymentCancel(requestDto);
        return new BaseResponse<>();
    }
}
