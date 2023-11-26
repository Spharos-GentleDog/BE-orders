package egenius.orders.domain.orders.entity;

import egenius.orders.domain.orders.entity.converter.VendorsOrderListStatusConverter;
import egenius.orders.domain.orders.entity.enums.VendorsOrderListStatus;
import egenius.orders.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VendorsOrderList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", length = 20, nullable = false)
    private String orderNumber;

    @Column(name = "vendor_email", nullable = false)
    private String vendorEmail;

    @Column(name = "brand_name", length = 100, nullable = false)
    private String brandName;

    @Column(name = "brand_logo_image_url", length = 100)
    private String brandLogoImageUrl;

    @Column(name = "order_type", columnDefinition = "tinyint(1)", nullable = false)
    private Integer orderType;

    @Convert(converter = VendorsOrderListStatusConverter.class)
    @Column(name = "vendors_order_list_status", columnDefinition = "tinyint", nullable = false)
    private VendorsOrderListStatus vendorsOrderListStatus;

    @Column(name = "user_email", length = 320)
    private String userEmail;

    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;

    @Column(name = "user_phone_number", length = 20, nullable = false)
    private String userPhoneNumber;

    @Column(name = "orders_request_message", length = 100)
    private String ordersRequestMessage;

    @Column(name = "dog_id")
    private Long dogId;

    @Column(name = "delivery_fee", nullable = false)
    private Integer deliveryFee;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "delete_status", columnDefinition = "tinyint(1)", nullable = false)
    private Integer orderDeleteStatus;

    @Column(name = "group_id")
    private Long groupId;

    @Builder.Default
    @OneToMany(mappedBy = "vendorsOrderList", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "delivery_orders_id", referencedColumnName = "id")
    private Delivery delivery;


}
