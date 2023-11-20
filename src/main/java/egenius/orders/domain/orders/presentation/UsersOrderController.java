package egenius.orders.domain.orders.presentation;

import egenius.orders.domain.orders.application.user.UserOrdersService;
import egenius.orders.domain.orders.dto.in.OrderRegisterRequestDto;
import egenius.orders.domain.orders.dto.out.OrdersSuccessResponseDto;
import egenius.orders.domain.orders.webdto.in.OrdersRegisterInWebDto;
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

    private final UserOrdersService userOrdersService;
    private final ModelMapper modelMapper;
    /**
     * 1. 주문 생성
     * 2. 주문 조회
     *
     * @return
     */

    // 1. 주문 생성

    @Operation(summary = "주문 생성", description = "주문 생성", tags = { "Orders" })
    @PostMapping("")
    public BaseResponse<OrdersSuccessResponseDto> createOrder(@RequestHeader("userEmail") String userEmail,
                                                              @RequestBody OrdersRegisterInWebDto ordersRegisterInWebDto) {
        OrderRegisterRequestDto orderRegisterRequestDto = modelMapper.map(ordersRegisterInWebDto,
                OrderRegisterRequestDto.class);
        OrdersSuccessResponseDto ordersSuccessResponseDto =  userOrdersService.registerOrders(userEmail, orderRegisterRequestDto);
        return new BaseResponse<>(ordersSuccessResponseDto);
    }

//    @Operation(summary = "유저 주문 요약 조회", description = "유저 주문 요약 무한스크롤 조회", tags = { "Orders" })
//    @GetMapping("/summary")
//    public BaseResponse<Slice<OrdersSummaryFindOutWebDto>> getOrdersSummary(@RequestHeader("userEmail") String userEmail,
//                                                                            @PageableDefault(size = 10, sort = "createAt", direction = DESC) Pageable pageable,
//                                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate
//                                                                            ) {
//
//        // 기존 코드에서 OrdersSummaryOutResponseDto 대신 OrdersSummaryFindOutWebDto 사용
//        // OrdersSummaryOutResponseDto를 가져와서 OrdersSummaryFindOutWebDto로 매핑
//        Slice<OrdersSummaryOutResponseDto> ordersSummarySlice =
//                userOrdersService.getOrdersSummary(userEmail, pageable, startDate);
//
//        return new BaseResponse<>(resultSlice);
//    }



//    @Operation(summary = "유저 주문 상세 조회", description = "유저 주문 상세 내역 무한스크롤 조회", tags = { "Orders" })
//    @GetMapping("")
//    public BaseResponse<OrdersDetailListOutWebDto> getUserOrdersDetail(Pageable pageable,
//                                    @RequestHeader("userEmail") String userEmail) {
//
//        OrdersListOutWebDto ordersListOutWebDto = ordersService.getUserOrders(pageable, userEmail);
//        return new BaseResponse<>(OrdersDetailListOutWebDto);
//
//    }


}
