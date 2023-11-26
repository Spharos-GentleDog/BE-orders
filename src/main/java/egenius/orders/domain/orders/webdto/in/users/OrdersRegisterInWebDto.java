package egenius.orders.domain.orders.webdto.in.users;

import egenius.orders.domain.orders.dto.in.users.DeliveryOrdersInRequestDto;
import egenius.orders.domain.orders.dto.in.users.VendorsOrderListInRequestDto;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRegisterInWebDto {
    private DeliveryOrdersInRequestDto deliveryOrdersInRequestDto;
    private List<VendorsOrderListInRequestDto> vendorsOrderListInRequestDto;

}
