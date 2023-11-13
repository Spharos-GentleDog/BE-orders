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
public class VendorOrdersList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_orders_id", nullable = false)
    private VendorOrdersList vendorOrdersList;

    @Column(name = "orders_number", length = 20, nullable = false)
    private String ordersNumber;

    @Column(name = "vendor_email", nullable = false)
    private String vendorEmail;

    @Column(name = "brand_name", length = 100, nullable = false)
    private String brandName;

    @Column(name = "brand_logo_url", length = 100, nullable = false)
    private String brandLogoUrl;

    @Column(name = "delivery_price", nullable = false)
    private Long deliveryPrice;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

}
