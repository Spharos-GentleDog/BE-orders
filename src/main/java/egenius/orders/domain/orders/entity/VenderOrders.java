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
public class VenderOrders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vender_orders_id", nullable = false)
    private VenderOrders venderOrders;

    @Column(name = "orders_number", length = 20, nullable = false)
    private String ordersNumber;

    @Column(name = "vender_id", nullable = false)
    private Long venderId;

    @Column(name = "delivery_price", nullable = false)
    private Long deliveryPrice;



}
