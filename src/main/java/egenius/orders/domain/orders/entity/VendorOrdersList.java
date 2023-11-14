package egenius.orders.domain.orders.entity;

import egenius.orders.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @JoinColumn(name = "orders_id", referencedColumnName = "id", nullable = false)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_orders_id", referencedColumnName = "id", nullable = false)
    private Delivery delivery;

    @Column(name = "orders_number", length = 20, nullable = false)
    private String ordersNumber;

    @Column(name = "vendor_email", nullable = false)
    private String vendorEmail;

    @Column(name = "brand_name", length = 100, nullable = false)
    private String brandName;

    @Column(name = "brand_logo_url", length = 100)
    private String brandLogoUrl;

    @Column(name = "delivery_fee", nullable = false)
    private Long deliveryFee;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;
    
    // 동환님 코드 참고 판매자주문 리스트에서 주문 상세 리스트 정보를 저장하는 방식
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_detail_id", referencedColumnName = "id")
    private List<OrdersDetail> ordersDetailList;

    // 주문 세부정보 업데이트
    public void updateOrdersDetailList(List ordersDetailList) {
        this.ordersDetailList = ordersDetailList;
    }


}
