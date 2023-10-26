package egenius.orders.domain.payment.dto;

import egenius.orders.domain.payment.entity.PaymentMethod;
import egenius.orders.domain.payment.entity.PaymentStatus;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

// modelMapper에서 mapping을 할때 기본 생성자(NoArgs)가 꼭 필요하다
// java에서는 생성자를 만들어주지 않으면 자동으로 기본 생성자를 생성해준다 -> 아무런 어노테이션을 붙여주지 않아도 됨!
@Getter
@ToString
public class PaymentRequestDto {
    private String paymentKey;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private Integer paymentTotalAmount;

    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    private Boolean isPartial;

    private String receipt_url;

    private Integer balanceAmount;
}
