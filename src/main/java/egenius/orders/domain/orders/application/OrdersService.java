package egenius.orders.domain.orders.application;

import egenius.orders.domain.orders.dto.RegisterInRequestDto;

public interface OrdersService {

    void registerOrders(RegisterInRequestDto registerInRequestDto);
}
