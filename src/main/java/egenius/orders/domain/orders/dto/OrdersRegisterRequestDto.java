package egenius.orders.domain.orders.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRegisterRequestDto {
    private DeliveryOrdersInRequestDto deliveryOrdersInRequestDto;
    private OrdersInRequestDto ordersInRequestDto;
    private List<VendorOrdersListInRequestDto> vendorOrdersListInRequestDto;

}
