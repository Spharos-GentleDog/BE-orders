package egenius.orders.domain.orders.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderDetail is a Querydsl query type for OrderDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderDetail extends EntityPathBase<OrderDetail> {

    private static final long serialVersionUID = 1837867690L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderDetail orderDetail = new QOrderDetail("orderDetail");

    public final egenius.orders.global.common.QBaseTimeEntity _super = new egenius.orders.global.common.QBaseTimeEntity(this);

    public final NumberPath<Integer> couponDiscountPrice = createNumber("couponDiscountPrice", Integer.class);

    public final NumberPath<Long> couponId = createNumber("couponId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath productColor = createString("productColor");

    public final NumberPath<Integer> productDeleteStatus = createNumber("productDeleteStatus", Integer.class);

    public final NumberPath<Long> productDetailId = createNumber("productDetailId", Long.class);

    public final NumberPath<Integer> productDiscountRate = createNumber("productDiscountRate", Integer.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productImageUrl = createString("productImageUrl");

    public final StringPath productName = createString("productName");

    public final EnumPath<egenius.orders.domain.orders.entity.enums.OrderDetailStatus> productOrderStatus = createEnum("productOrderStatus", egenius.orders.domain.orders.entity.enums.OrderDetailStatus.class);

    public final NumberPath<Integer> productPrice = createNumber("productPrice", Integer.class);

    public final StringPath productSize = createString("productSize");

    public final NumberPath<Integer> productStock = createNumber("productStock", Integer.class);

    public final QRefundAndExchange refundAndExchange;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOrderDetail(String variable) {
        this(OrderDetail.class, forVariable(variable), INITS);
    }

    public QOrderDetail(Path<? extends OrderDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderDetail(PathMetadata metadata, PathInits inits) {
        this(OrderDetail.class, metadata, inits);
    }

    public QOrderDetail(Class<? extends OrderDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.refundAndExchange = inits.isInitialized("refundAndExchange") ? new QRefundAndExchange(forProperty("refundAndExchange")) : null;
    }

}

