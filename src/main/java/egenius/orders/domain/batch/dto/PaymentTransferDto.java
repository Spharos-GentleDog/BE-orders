package egenius.orders.domain.batch.dto;

import egenius.orders.domain.payment.entity.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
@AllArgsConstructor
public class PaymentTransferDto {
    private Integer productAmount;
    private String productCode;
    private String productMainImageUrl;
    private Integer count;
    private String vendorEmail;
    private PaymentMethod paymentMethod;
    private LocalDateTime paidAt;
    private String productName;
}
