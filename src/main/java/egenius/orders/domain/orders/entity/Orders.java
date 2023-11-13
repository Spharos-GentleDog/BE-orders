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
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orders_number", length = 20, nullable = false)
    private String ordersNumber;

    @Column(name = "user_email", length = 30, nullable = false)
    private String userEmail;

    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    @Column(name = "orders_request_message", length = 100)
    private String ordersRequestMessage;

    @Column(name = "orders_type", columnDefinition = "tinyint(1)", nullable = false)
    private Integer ordersType;

    @Column(name = "usred_point", nullable = false)
    private Integer usedPoint;

}
