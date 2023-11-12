package egenius.orders.domain.orders.presentation;

import egenius.orders.domain.orders.application.OrdersService;
import egenius.orders.domain.orders.dto.RegisterInRequestDto;
import egenius.orders.domain.orders.webdto.RegisterInWebDto;
import egenius.orders.domain.payment.dto.PaymentRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersService ordersService;
    private final ModelMapper modelMapper;
    /**
     * 1. 주문 생성
     * 2. 주문 조회
     */

    // 1. 주문 생성

    @Operation(summary = "주문", description = "주문내역 저장", tags = { "Orders" })
    @PostMapping("")
    public void createOrder(@RequestBody RegisterInWebDto registerInWebDto) {

        RegisterInRequestDto registerInRequestDto = modelMapper.map(registerInWebDto, RegisterInRequestDto.class);
        ordersService.registerOrders(registerInRequestDto);

    }
    @Operation(summary = "주문", description = "주문내역 조회", tags = { "Orders" })
    @GetMapping("")
    public void getOrder() {

    }

    // 2. 주문 조회
}
