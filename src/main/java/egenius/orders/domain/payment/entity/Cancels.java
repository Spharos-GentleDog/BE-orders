package egenius.orders.domain.payment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Cancels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @Column(name = "canceled_at")
    private String canceledAt;

    @Column(name = "cancel_amount")
    private Integer cancelAmount;

    @Column(name = "transaction_key")
    private String transactionKey;

    @Column(name = "receipt_key")
    private String receiptKey;


}
