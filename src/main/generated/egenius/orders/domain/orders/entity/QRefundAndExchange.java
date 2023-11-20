package egenius.orders.domain.orders.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRefundAndExchange is a Querydsl query type for RefundAndExchange
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefundAndExchange extends EntityPathBase<RefundAndExchange> {

    private static final long serialVersionUID = -673062995L;

    public static final QRefundAndExchange refundAndExchange = new QRefundAndExchange("refundAndExchange");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> ordersDetailId = createNumber("ordersDetailId", Long.class);

    public final StringPath refundAndExchangeReason = createString("refundAndExchangeReason");

    public final EnumPath<egenius.orders.domain.orders.entity.enums.RefundAndExchangeStatus> refundAndExchangeStatus = createEnum("refundAndExchangeStatus", egenius.orders.domain.orders.entity.enums.RefundAndExchangeStatus.class);

    public final NumberPath<Integer> refundAndExchangeStock = createNumber("refundAndExchangeStock", Integer.class);

    public final NumberPath<Integer> refundAndExchangeType = createNumber("refundAndExchangeType", Integer.class);

    public QRefundAndExchange(String variable) {
        super(RefundAndExchange.class, forVariable(variable));
    }

    public QRefundAndExchange(Path<? extends RefundAndExchange> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRefundAndExchange(PathMetadata metadata) {
        super(RefundAndExchange.class, metadata);
    }

}

