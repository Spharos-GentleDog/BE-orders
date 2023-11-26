package egenius.orders.domain.payment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = -1039191690L;

    public static final QPayment payment = new QPayment("payment");

    public final DateTimePath<java.time.LocalDateTime> approvedAt = createDateTime("approvedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> balanceAmount = createNumber("balanceAmount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPartial = createBoolean("isPartial");

    public final StringPath paymentKey = createString("paymentKey");

    public final EnumPath<egenius.orders.domain.payment.entity.enums.PaymentMethod> paymentMethod = createEnum("paymentMethod", egenius.orders.domain.payment.entity.enums.PaymentMethod.class);

    public final EnumPath<egenius.orders.domain.payment.entity.enums.PaymentStatus> paymentStatus = createEnum("paymentStatus", egenius.orders.domain.payment.entity.enums.PaymentStatus.class);

    public final NumberPath<Integer> paymentTotalAmount = createNumber("paymentTotalAmount", Integer.class);

    public final ListPath<ProductPayment, QProductPayment> productPaymentList = this.<ProductPayment, QProductPayment>createList("productPaymentList", ProductPayment.class, QProductPayment.class, PathInits.DIRECT2);

    public final StringPath receipt_url = createString("receipt_url");

    public final DateTimePath<java.time.LocalDateTime> requestedAt = createDateTime("requestedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> usedPoint = createNumber("usedPoint", Integer.class);

    public QPayment(String variable) {
        super(Payment.class, forVariable(variable));
    }

    public QPayment(Path<? extends Payment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayment(PathMetadata metadata) {
        super(Payment.class, metadata);
    }

}

