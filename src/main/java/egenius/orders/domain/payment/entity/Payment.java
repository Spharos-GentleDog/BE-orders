package egenius.orders.domain.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_key")
    private String paymentKey;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "payment_total_amount")
    private Integer paymentTotalAmount;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "is_partial")
    private Boolean isPartial;

    @Column(name = "receipt_url")
    private String receipt_url; // receipt의 url과 매핑됨
}
