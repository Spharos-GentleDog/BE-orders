package egenius.orders.domain.orders.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVendorsOrderList is a Querydsl query type for VendorsOrderList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVendorsOrderList extends EntityPathBase<VendorsOrderList> {

    private static final long serialVersionUID = -745418090L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVendorsOrderList vendorsOrderList = new QVendorsOrderList("vendorsOrderList");

    public final egenius.orders.global.common.QBaseTimeEntity _super = new egenius.orders.global.common.QBaseTimeEntity(this);

    public final StringPath brandLogoImageUrl = createString("brandLogoImageUrl");

    public final StringPath brandName = createString("brandName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QDelivery delivery;

    public final NumberPath<Integer> deliveryFee = createNumber("deliveryFee", Integer.class);

    public final NumberPath<Long> dogId = createNumber("dogId", Long.class);

    public final NumberPath<Long> groupId = createNumber("groupId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> orderDeleteStatus = createNumber("orderDeleteStatus", Integer.class);

    public final ListPath<OrderDetail, QOrderDetail> orderDetailList = this.<OrderDetail, QOrderDetail>createList("orderDetailList", OrderDetail.class, QOrderDetail.class, PathInits.DIRECT2);

    public final StringPath orderNumber = createString("orderNumber");

    public final StringPath ordersRequestMessage = createString("ordersRequestMessage");

    public final NumberPath<Integer> orderType = createNumber("orderType", Integer.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userName = createString("userName");

    public final StringPath userPhoneNumber = createString("userPhoneNumber");

    public final StringPath vendorEmail = createString("vendorEmail");

    public final EnumPath<egenius.orders.domain.orders.entity.enums.VendorsOrderListStatus> vendorsOrderListStatus = createEnum("vendorsOrderListStatus", egenius.orders.domain.orders.entity.enums.VendorsOrderListStatus.class);

    public QVendorsOrderList(String variable) {
        this(VendorsOrderList.class, forVariable(variable), INITS);
    }

    public QVendorsOrderList(Path<? extends VendorsOrderList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVendorsOrderList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVendorsOrderList(PathMetadata metadata, PathInits inits) {
        this(VendorsOrderList.class, metadata, inits);
    }

    public QVendorsOrderList(Class<? extends VendorsOrderList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new QDelivery(forProperty("delivery")) : null;
    }

}

