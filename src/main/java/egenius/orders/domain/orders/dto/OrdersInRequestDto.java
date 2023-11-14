package egenius.orders.domain.orders.dto;

import lombok.Getter;

@Getter
public class OrdersInRequestDto {

    private String userEmail;
    private String userName;
    private String userPhoneNumber;
    private String ordersRequestMessage;
    private Integer ordersType;
    private Integer usedPoint;
}
