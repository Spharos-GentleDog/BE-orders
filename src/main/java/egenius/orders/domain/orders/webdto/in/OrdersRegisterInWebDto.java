package egenius.orders.domain.orders.webdto.in;

import egenius.orders.domain.orders.dto.in.DeliveryOrdersInRequestDto;
import egenius.orders.domain.orders.dto.in.VendorsOrderListInRequestDto;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRegisterInWebDto {
    private DeliveryOrdersInRequestDto deliveryOrdersInRequestDto;
    private List<VendorsOrderListInRequestDto> vendorsOrderListInRequestDto;

}
