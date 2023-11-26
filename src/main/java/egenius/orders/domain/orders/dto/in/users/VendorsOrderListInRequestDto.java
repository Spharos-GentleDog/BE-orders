package egenius.orders.domain.orders.dto.in.users;

import lombok.Getter;

import java.util.List;

@Getter
public class VendorsOrderListInRequestDto {

    private String vendorEmail;
    private String brandName;
    private String brandLogoImageUrl;
    private String userName;
    private String userPhoneNumber;
    private String ordersRequestMessage;
    private Integer ordersType;
    private Long dogId;
    private Integer deliveryFee;
    private Integer totalPrice;

    private List<OrderDetailInRequestDto> orderDetailList;

}
