package egenius.orders.domain.payment.dto;

import egenius.orders.domain.payment.entity.PaymentMethod;
import egenius.orders.domain.payment.entity.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor // modelMapper에서 mapping을 할때 기본 생성자(noArgs)가 꼭 필요하다 !!
@ToString
public class PaymentRequestDto {
    private String paymentKey;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private Integer paymentTotalAmount;

    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    private Boolean isPartial;

    private String receipt_url; // receipt의 url과 매핑됨
}
