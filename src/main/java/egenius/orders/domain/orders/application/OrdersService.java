package egenius.orders.domain.orders.application;

import egenius.orders.domain.orders.dto.OrdersRegisterRequestDto;

public interface OrdersService {

    void registerOrders(OrdersRegisterRequestDto ordersRegisterRequestDto);
}
