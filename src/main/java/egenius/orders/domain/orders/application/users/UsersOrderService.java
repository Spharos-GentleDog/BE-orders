package egenius.orders.domain.orders.application.users;

import egenius.orders.domain.orders.dto.in.users.OrderRegisterRequestDto;
import egenius.orders.domain.orders.dto.out.users.OrdersSuccessResponseDto;
import egenius.orders.domain.orders.dto.out.users.VendorsOrderSearchOutResponseDto;

public interface UsersOrderService {

    // 주문 생성
    OrdersSuccessResponseDto registerOrders(String userEmail, OrderRegisterRequestDto orderRegisterRequestDto);

    // 주문 요약 조회
    VendorsOrderSearchOutResponseDto getOrdersSummary(String userEmail, Long groupId);
}
