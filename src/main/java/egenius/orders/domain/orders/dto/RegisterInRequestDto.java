package egenius.orders.domain.orders.dto;

import lombok.Getter;

@Getter
public class RegisterInRequestDto {
    private String ordersName;
    private String ordersPhoneNumber;
    private String ordersEmail;
    private String ordersRequestMessage;
    private String ordersType;
    private Long vendorId;
    private String brandName;
    private String brandLogoImageUrl;
    private Integer deliveryFee;
    private Integer totalPrice;
    private String productName;
    private String productImageUrl;
    private Integer productPrice;
    private Integer productQuantity;
    private String productSize;
    private String productColor;
    private Integer productStatus;
    private Integer productDiscountRate;
    private Long couponId;
    private Integer couponDiscountPrice;
    private String addressPhoneNumber;
    private String addressName;
    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;
    private String entrancePassword;
    private String deliveryRequestMessage;
}
