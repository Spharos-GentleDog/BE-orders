package egenius.orders.domain.payment.entity;

import egenius.orders.global.common.exception.BaseException;
import egenius.orders.global.common.response.BaseResponseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_key", length = 200)
    private String paymentKey;

    @Column(name = "payment_method", length = 1)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_status", columnDefinition = "tinyint", length = 10)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_total_amount")
    private Integer paymentTotalAmount;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "is_partial", columnDefinition = "tinyint(1)")
    private Boolean isPartial;

    @Column(name = "receipt_url", length = 100)
    private String receipt_url;

    @Column(name = "balance_amount")
    private Integer balanceAmount;


    /**
     * Payment
     * 1. 결제상태 업데이트
     * 2. 취소가능 잔고 업데이트
     */

    // 1. 결제상태 업데이트
    public void updateStatus(PaymentStatus status) {
        this.paymentStatus = status;
    }

    // 2. 취소 가능 잔고 업데이트
    public void updateBalanceAmount(Integer cancledAmount) {
        if (this.balanceAmount >= cancledAmount) {
            this.balanceAmount = this.balanceAmount - cancledAmount;
        } else {
            throw new BaseException(BaseResponseStatus.CANCELED_AMOUNT_EXCEEDED);
        }
    }
}
