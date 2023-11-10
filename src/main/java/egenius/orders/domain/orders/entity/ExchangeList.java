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
public class ExchangeList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orders_detail_id", referencedColumnName = "id", nullable = false)
    private OrdersDetail ordersDetail;

    @Column(name = "exchange_product_id", nullable = false)
    private Long exchangeProductId;

    @Column(name = "orders_number", nullable = false)
    private String ordersNumber;

    @Column(name = "exchange_product_quantity", nullable = false)
    private Short exchangeProductQuantity;

    @Column(name = "exchange_product_price", nullable = false)
    private Integer exchangeProductPrice;

    @Column(name = "exchange_status", columnDefinition = "tinyint", nullable = false)
    private Integer exchangeStatus;

    @Column(name = "exchange_reason", length = 100, nullable = false)
    private String exchangeReason;

    @Column(name = "exchange_orders_number", length = 20, nullable = false)
    private String exchangeOrdersNumber;

    @Column(name = "exchange_product_size", length = 20, nullable = false)
    private String exchangeProductSize;

    @Column(name = "exchange_product_color", length = 20, nullable = false)
    private String exchangeProductColor;
}
