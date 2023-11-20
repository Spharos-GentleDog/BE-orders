package egenius.orders.domain.orders.dto.in;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRegisterRequestDto {
    private DeliveryOrdersInRequestDto deliveryOrdersInRequestDto;
    private List<VendorsOrderListInRequestDto> vendorsOrderListInRequestDto;

}