package egenius.orders.domain.orders.application.user;

import egenius.orders.domain.orders.dto.in.OrderRegisterRequestDto;
import egenius.orders.domain.orders.dto.out.OrdersSuccessResponseDto;

public interface UserOrdersService {

    // 주문 생성
    OrdersSuccessResponseDto registerOrders(String userEmail, OrderRegisterRequestDto orderRegisterRequestDto);

    // 주문 요약 조회
//    Slice<OrdersSummaryOutResponseDto> getOrdersSummary(String userEmail, Pageable pageable, LocalDate startDate);
}
