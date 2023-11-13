package egenius.orders.domain.orders.webdto;

import egenius.orders.domain.orders.dto.DeliveryOrdersInRequestDto;
import egenius.orders.domain.orders.dto.OrdersInRequestDto;
import egenius.orders.domain.orders.dto.VendorOrdersListInRequestDto;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRegisterInWebDto {
    private DeliveryOrdersInRequestDto deliveryOrdersInRequestDto;
    private OrdersInRequestDto ordersInRequestDto;
    private List<VendorOrdersListInRequestDto> vendorOrdersListInRequestDto;

}
