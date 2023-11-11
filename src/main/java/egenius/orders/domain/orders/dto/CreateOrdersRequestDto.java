package egenius.orders.domain.orders.dto;

import lombok.Getter;

@Getter
public class CreateOrdersRequestDto {
    private String ordersName;
    private String ordersPhoneNumber;
    private String ordersEmail;
    private String orders_request_message;
    private String orders_type;
    private Long venderId;
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
