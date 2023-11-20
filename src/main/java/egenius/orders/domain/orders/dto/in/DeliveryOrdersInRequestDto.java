package egenius.orders.domain.orders.dto.in;

import lombok.Getter;

@Getter
public class DeliveryOrdersInRequestDto {

    private String recipientName;
    private String recipientAddress;
    private String recipientPhoneNumber;
    private String entrancePassword;
    private String deliveryRequestMessage;

}
