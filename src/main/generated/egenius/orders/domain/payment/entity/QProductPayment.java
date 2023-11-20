package egenius.orders.domain.payment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductPayment is a Querydsl query type for ProductPayment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductPayment extends EntityPathBase<ProductPayment> {

    private static final long serialVersionUID = 36054183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductPayment productPayment = new QProductPayment("productPayment");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPayment payment;

    public final NumberPath<Integer> productAmount = createNumber("productAmount", Integer.class);

    public final StringPath productCode = createString("productCode");

    public final StringPath productMainImageUrl = createString("productMainImageUrl");

    public final StringPath productName = createString("productName");

    public final StringPath vendorEmail = createString("vendorEmail");

    public QProductPayment(String variable) {
        this(ProductPayment.class, forVariable(variable), INITS);
    }

    public QProductPayment(Path<? extends ProductPayment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductPayment(PathMetadata metadata, PathInits inits) {
        this(ProductPayment.class, metadata, inits);
    }

    public QProductPayment(Class<? extends ProductPayment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.payment = inits.isInitialized("payment") ? new QPayment(forProperty("payment")) : null;
    }

}

