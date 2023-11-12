package egenius.orders.domain.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductPaymentDto {
    private String vendorEmail;

    private String productName;

    private String productCode;

    private String productMainImageUrl;

    private Integer productAmount;

    private Integer count;
}
