//package egenius.orders.domain.orders.infrastructure;
//
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import egenius.orders.domain.orders.dto.out.OrdersSummaryOutResponseDto;
//import egenius.orders.domain.orders.dto.out.QOrdersSummaryOutResponseDto;
//import egenius.orders.domain.orders.dto.out.QOrdersSummaryOutResponseDto_OrdersDetailOutResponseDto;
//import egenius.orders.domain.orders.entity.converter.DeliveryConverter;
//import jakarta.persistence.Converter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.data.domain.SliceImpl;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static egenius.orders.domain.orders.entity.QVendorOrdersList.vendorOrdersList1;
//import static egenius.orders.domain.orders.entity.QOrdersDetail.ordersDetail1;
//import static egenius.orders.domain.orders.entity.QOrders.orders1;
//import static egenius.orders.domain.orders.entity.QDelivery.delivery1;
//
//@RequiredArgsConstructor
//public class CustomOrdersDetailImple implements CustomOrdersDetailRepository {
//
//    private final JPAQueryFactory queryFactory;
//    private final DeliveryConverter deliveryConverter;
//
//    @Override
//    public Slice<OrdersSummaryOutResponseDto> findByFilter(Long ordersId, String userEmail, Pageable pageable) {
//        List<OrdersSummaryOutResponseDto> results = queryFactory
//                .select(new QOrdersSummaryOutResponseDto(
//                        orders1.ordersNumber,
//                        vendorOrdersList1.brandName,
//                        vendorOrdersList1.brandLogoUrl,
//                        vendorOrdersList1.vendorEmail,
//                        ordersDetail1.productPrice.multiply(ordersDetail1.productStock).sum(),
//                        orders1.createdAt,
//                        delivery1.updatedAt,
//                        deliveryConverter.convertToEntityAttribute(orders1.deliveryStatus),
//                        new QOrdersSummaryOutResponseDto_OrdersDetailOutResponseDto(
//                                ordersDetail1.productId,
//                                ordersDetail1.productName,
//                                ordersDetail1.productImageUrl,
//                                ordersDetail1.productPrice,
//                                ordersDetail1.productSize,
//                                ordersDetail1.productColor,
//                                ordersDetail1.productStock,
//                                ordersDetail1.productOrdersStatus,
//                                ordersDetail1.productDiscountRate))
//                .from(vendorOrdersList1) // 가 맞을까 orders1가 맞을까
//                .join(delivery1).on(vendorOrdersList1.id.eq(delivery1.id))
//                .join(ordersDetail1).on(vendorOrdersList1.id.eq(ordersDetail1.id))
//                .join(orders1).on(orders1.id.eq(ordersDetail1.id))
//                .where(ltOrdersId(ordersId),
//                        orders1.userEmail.eq(userEmail))
//                .groupBy(orders1.id)
//                .orderBy(orders1.id.desc())
//                .limit(pageable.getPageSize() + 1)
//                .fetch());
//        return checkLastPage(pageable, results);
//    }
//
//    /**
//     * BooleanExpression을 사용하는 이유
//     * 1) 가독성이 뛰어남
//     * 2) null 반환 시 Where절에서 무시되기 때문에 안전
//     * 3) and, or같은 메소드들을 이용해 BooleanExpression을 조합해 새로운 BooleanExpression를 만들 수 있어
//     * 재사용성이 좋기 때문에 !
//     *
//     */
//
//    // no-offset
//    private BooleanExpression ltOrdersId(Long ordersId) {
//        if (ordersId == null) {
//            return null; // BooleanExpression 자리에 null이 반환되면 조건문에서 자동으로 제거된다
//        }
//        return orders1.id.lt(ordersId);
//    }
//
//
//
//
//    // 무한 스크롤
//    private Slice<OrdersSummaryOutResponseDto> checkLastPage(Pageable pageable, List<OrdersSummaryOutResponseDto> results){
//        boolean hasNext = false;
//        // 페이지 사이즈보다 결과가 많으면 다음 페이지가 있다고 판단
//        if (results.size() > pageable.getPageSize()) {
//            hasNext = true;
//            results.remove(pageable.getPageSize());
//        }
//        return new SliceImpl<>(results, pageable, hasNext);
//    }
//
//    /**
//     * @param userEmail
//     * @param startDate
//     * @param endDate
//     * @param pageable
//     * @return
//     */
//
//
////    // 2. 주문 상세 키워드 검색
////    public List<OrdersDetail> getOrdersDetailByOrdersId(Long ordersId) {
////        return queryFactory
////                .selectFrom(ordersDetail)
////                .where(ordersDetail.)
////                .fetch();
////    }
//
//}
