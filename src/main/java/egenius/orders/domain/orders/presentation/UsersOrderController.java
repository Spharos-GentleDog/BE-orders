package egenius.orders.domain.orders.presentation;

import egenius.orders.domain.orders.application.users.UsersOrderService;
import egenius.orders.domain.orders.dto.in.users.OrderRegisterRequestDto;
import egenius.orders.domain.orders.dto.out.users.OrdersSuccessResponseDto;
import egenius.orders.domain.orders.webdto.in.users.OrdersRegisterInWebDto;
import egenius.orders.domain.orders.webdto.out.users.VendorsOrderInfoOutWebDto;
import egenius.orders.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders/user")
@RequiredArgsConstructor
@Slf4j
public class UsersOrderController {

    private final UsersOrderService usersOrderService;
    private final ModelMapper modelMapper;
    /**
     * 1. 주문 생성
     * 2. 주문 조회
     *
     * @return
     */

    // 1. 주문 생성

    @Operation(summary = "주문 생성", description = "주문 생성", tags = { "Orders User" })
    @PostMapping("")
    public BaseResponse<OrdersSuccessResponseDto> createOrder(@RequestHeader("userEmail") String userEmail,
                                                              @RequestBody OrdersRegisterInWebDto ordersRegisterInWebDto) {
        log.info("ordersRegisterInWebDto : {}", ordersRegisterInWebDto.getVendorsOrderListInRequestDto().get(0).getUserName());
        OrderRegisterRequestDto orderRegisterRequestDto = modelMapper.map(ordersRegisterInWebDto,
                OrderRegisterRequestDto.class);
        OrdersSuccessResponseDto ordersSuccessResponseDto =  usersOrderService.registerOrders(userEmail, orderRegisterRequestDto);
        return new BaseResponse<>(ordersSuccessResponseDto);
    }

    @Operation(summary = "유저 주문 조회", description = "유저 주문 무한스크롤 조회", tags = { "Orders User" })
    @GetMapping("")
    public BaseResponse<VendorsOrderInfoOutWebDto> getOrdersSummary(@RequestHeader("userEmail") String userEmail,
                                                                    @RequestParam(value = "groupId", required = false)
                                                                           Long groupId
    ) {

       VendorsOrderInfoOutWebDto vendorsOrderInfoOutWebDtos = modelMapper.map(
               usersOrderService.getOrdersSummary(userEmail, groupId), VendorsOrderInfoOutWebDto.class);

        return new BaseResponse<>(vendorsOrderInfoOutWebDtos);
    }

    @Operation(summary = "유저 주문 삭제", description = "유저 주문 삭제", tags = { "Orders User" })
    @DeleteMapping("/{orderNumber}")
    public BaseResponse<Void> deleteOrder(@RequestHeader("userEmail") String userEmail,
                                          @PathVariable("orderNumber") String orderNumber) {

        usersOrderService.deleteOrder(userEmail, orderNumber);
        return new BaseResponse<>();
    }
}
