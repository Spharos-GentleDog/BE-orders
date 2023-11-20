package egenius.orders.domain.payment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCancels is a Querydsl query type for Cancels
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCancels extends EntityPathBase<Cancels> {

    private static final long serialVersionUID = 297705641L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCancels cancels = new QCancels("cancels");

    public final NumberPath<Integer> cancelAmount = createNumber("cancelAmount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> canceledAt = createDateTime("canceledAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPayment payment;

    public final StringPath receiptKey = createString("receiptKey");

    public final StringPath transactionKey = createString("transactionKey");

    public QCancels(String variable) {
        this(Cancels.class, forVariable(variable), INITS);
    }

    public QCancels(Path<? extends Cancels> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCancels(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCancels(PathMetadata metadata, PathInits inits) {
        this(Cancels.class, metadata, inits);
    }

    public QCancels(Class<? extends Cancels> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.payment = inits.isInitialized("payment") ? new QPayment(forProperty("payment")) : null;
    }

}

