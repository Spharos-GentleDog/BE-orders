package egenius.orders.domain.orders.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = 397034089L;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final egenius.orders.global.common.QBaseTimeEntity _super = new egenius.orders.global.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath deliveryRequestMessage = createString("deliveryRequestMessage");

    public final EnumPath<egenius.orders.domain.orders.entity.enums.DeliveryStatus> deliveryStatus = createEnum("deliveryStatus", egenius.orders.domain.orders.entity.enums.DeliveryStatus.class);

    public final StringPath entrancePassword = createString("entrancePassword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath recipientAddress = createString("recipientAddress");

    public final StringPath recipientName = createString("recipientName");

    public final StringPath recipientPhoneNumber = createString("recipientPhoneNumber");

    public final StringPath trackingNumber = createString("trackingNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QDelivery(String variable) {
        super(Delivery.class, forVariable(variable));
    }

    public QDelivery(Path<? extends Delivery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDelivery(PathMetadata metadata) {
        super(Delivery.class, metadata);
    }

}

