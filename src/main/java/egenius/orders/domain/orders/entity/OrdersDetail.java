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
public class OrdersDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_orders_id", nullable = false)
    private DeliveryOrders deliveryOrders;

    @Column(name = "product_detail_id", nullable = false)
    private Long productDetailId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "vender_id", nullable = false)
    private Long venderId;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;

    @Column(name = "product_size", length = 20, nullable = false)
    private String productSize;

    @Column(name = "product_color", length = 20, nullable = false)
    private String productColor;

    @Column(name = "product_status", columnDefinition = "tinyint", nullable = false)
    private Integer productStatus;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "coupon_discount_price")
    private Integer couponDiscountPrice;

    @Column(name = "total_favorite")
    private Integer totalFavorite;

}
