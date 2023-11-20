package egenius.orders.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGuest is a Querydsl query type for Guest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGuest extends EntityPathBase<Guest> {

    private static final long serialVersionUID = 1139615645L;

    public static final QGuest guest = new QGuest("guest");

    public final egenius.orders.global.common.QBaseTimeEntity _super = new egenius.orders.global.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath guestEmail = createString("guestEmail");

    public final StringPath guestName = createString("guestName");

    public final StringPath guestPhoneNumber = createString("guestPhoneNumber");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QGuest(String variable) {
        super(Guest.class, forVariable(variable));
    }

    public QGuest(Path<? extends Guest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGuest(PathMetadata metadata) {
        super(Guest.class, metadata);
    }

}

