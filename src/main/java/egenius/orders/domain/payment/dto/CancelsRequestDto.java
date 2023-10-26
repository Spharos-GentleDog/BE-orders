package egenius.orders.domain.payment.dto;

import egenius.orders.domain.payment.entity.Payment;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelsRequestDto {
    private String payment_id;

    private String canceledAt;

    private Integer cancelAmount;

    private String transactionKey;

    private String receiptKey;
}
