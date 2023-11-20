package egenius.orders.domain.orders.entity;

import egenius.orders.domain.orders.entity.converter.RefundAndExchangeStatusConverter;
import egenius.orders.domain.orders.entity.enums.RefundAndExchangeStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RefundAndExchange {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "refund_and_exchange_stock", nullable = false)
    private Integer refundAndExchangeStock;

    @Convert(converter = RefundAndExchangeStatusConverter.class)
    @Column(name = "refund_and_exchange_status", columnDefinition = "tinyint", nullable = false)
    private RefundAndExchangeStatus refundAndExchangeStatus;

    @Column(name = "refund_and_exchange_reason", length = 255, nullable = false)
    private String refundAndExchangeReason;

    @Column(name = "refund_and_exchange_type", columnDefinition = "tinyint(1)", nullable = false)
    private Integer refundAndExchangeType;

    @Column(name = "orders_detail_id", nullable = false)
    private Long ordersDetailId;

}
