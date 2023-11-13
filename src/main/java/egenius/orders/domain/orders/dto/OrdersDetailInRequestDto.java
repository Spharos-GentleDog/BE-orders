package egenius.orders.domain.orders.dto;

import lombok.Getter;

@Getter
public class OrdersDetailInRequestDto {

    private Long productId;
    private Long productDetailId;
    private String productName;
    private Integer productQuantity;
    private Integer productPrice;
    private String productSize;
    private String productColor;
    private Integer productStatus;
    private Integer productDiscountRate;
    private String productImageUrl;
    private Long couponId;
    private Integer couponDiscountPrice;

}
