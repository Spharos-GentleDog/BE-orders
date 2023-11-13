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

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_detail_id", nullable = false)
    private Long productDetailId;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "product_size", length = 20, nullable = false)
    private String productSize;

    @Column(name = "product_color", length = 20, nullable = false)
    private String productColor;

    @Column(name = "product_status", columnDefinition = "tinyint", nullable = false)
    private Integer productStatus;

    @Column(name = "product_discount_rate", nullable = false)
    private Integer productDiscountRate;

    @Column(name = "product_image_url", length = 100, nullable = false)
    private String productImageUrl;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "coupon_discount_price")
    private Integer couponDiscountPrice;

    // 상품 상태 변경
    public void updateProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

}
