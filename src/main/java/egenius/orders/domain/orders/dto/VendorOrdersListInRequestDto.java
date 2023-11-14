package egenius.orders.domain.orders.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class VendorOrdersListInRequestDto {

    private String vendorEmail;
    private Integer deliveryFee;
    private Integer totalPrice;
    private String brandName;
    private String brandLogoImageUrl;

    private List<OrdersDetailInRequestDto> ordersDetailInRequestDtoList;

}
