package egenius.orders.domain.payment.webdto;

import egenius.orders.domain.payment.entity.PaymentMethod;
import egenius.orders.domain.payment.entity.PaymentStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
public class PaymentInWebDto {
    private String paymentKey;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private Integer paymentTotalAmount;

    private Boolean isPartial;

    private String receipt_url;

    // String을 LocalDateTime으로 변환해서 받아옴
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime requestedAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime approvedAt;
}
