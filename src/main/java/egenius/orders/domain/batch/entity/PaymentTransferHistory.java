package egenius.orders.domain.batch.entity;

import egenius.orders.domain.payment.entity.PaymentMethod;
import egenius.orders.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "payment_transfer_history")
public class PaymentTransferHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_amount")
    private Long paymentAmount;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "vendor_email")
    private String vendorEmail;


}
