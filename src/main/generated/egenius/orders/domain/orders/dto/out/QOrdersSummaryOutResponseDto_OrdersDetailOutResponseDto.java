package egenius.orders.domain.orders.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * egenius.orders.domain.orders.dto.out.QOrdersSummaryOutResponseDto_OrdersDetailOutResponseDto is a Querydsl Projection type for OrdersDetailOutResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrdersSummaryOutResponseDto_OrdersDetailOutResponseDto extends ConstructorExpression<OrdersSummaryOutResponseDto.OrdersDetailOutResponseDto> {

    private static final long serialVersionUID = -1885376440L;

    public QOrdersSummaryOutResponseDto_OrdersDetailOutResponseDto(com.querydsl.core.types.Expression<Long> productId, com.querydsl.core.types.Expression<String> productName, com.querydsl.core.types.Expression<String> productImageUrl, com.querydsl.core.types.Expression<Integer> productPrice, com.querydsl.core.types.Expression<String> productSize, com.querydsl.core.types.Expression<String> productColor, com.querydsl.core.types.Expression<Integer> productStock, com.querydsl.core.types.Expression<Integer> productOrdersStatus, com.querydsl.core.types.Expression<Integer> productDiscountRate) {
        super(OrdersSummaryOutResponseDto.OrdersDetailOutResponseDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, String.class, String.class, int.class, int.class, int.class}, productId, productName, productImageUrl, productPrice, productSize, productColor, productStock, productOrdersStatus, productDiscountRate);
    }

}

