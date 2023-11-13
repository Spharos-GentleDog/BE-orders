package egenius.orders.domain.orders.dto;

import lombok.Getter;

@Getter
public class OrdersInRequestDto {

    private String userName;
    private String userPhoneNumber;
    private String ordersEmail;
    private String ordersRequestMessage;
    private String ordersType;
    private Integer usedPoint;
}
