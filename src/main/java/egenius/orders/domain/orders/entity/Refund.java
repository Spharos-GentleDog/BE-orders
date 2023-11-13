package egenius.orders.domain.orders.entity;

import egenius.orders.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Refund extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orders_detail_id", referencedColumnName = "id", nullable = false)
    private OrdersDetail ordersDetail;

    @Column(name = "vendor_email", nullable = false)
    private String vendorEmail;

    @Column(name = "orders_number", length = 20, nullable = false)
    private String ordersNumber;

    @Column(name = "refund_product_quantity", columnDefinition = "smallint", nullable = false)
    private Integer refundProductQuantity;

    @Column(name = "refund_product_price", nullable = false)
    private Integer refundProductPrice;

    @Column(name = "refund_status", columnDefinition = "tinyint", nullable = false)
    private Integer refundStatus;

    @Column(name = "refund_reason", length = 200, nullable = false)
    private String refundReason;

}
