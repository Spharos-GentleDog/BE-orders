package egenius.orders.domain.orders.infrastructure;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import egenius.orders.domain.orders.dto.out.users.OrderDetailOutResponseDto;
import egenius.orders.domain.orders.dto.out.users.VendorsOrderSummaryOutResponseDto;
import egenius.orders.domain.orders.entity.OrderDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static egenius.orders.domain.orders.entity.QDelivery.delivery;
import static egenius.orders.domain.orders.entity.QVendorsOrderList.vendorsOrderList;

@Slf4j
@RequiredArgsConstructor
public class VendorsOrderListRepositoryImpl implements VendorsOrderListRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final DeliveryRepository deliveryRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<VendorsOrderSummaryOutResponseDto> findByFilter(String userEmail, Long groupId) {
        // 주문서 요약 정보 조회
        List<VendorsOrderSummaryOutResponseDto> results = queryFactory
                .select(
                        Projections.fields(
                                VendorsOrderSummaryOutResponseDto.class,
                                vendorsOrderList.groupId,
                                vendorsOrderList.orderNumber,
                                vendorsOrderList.brandName,
                                vendorsOrderList.brandLogoImageUrl,
                                vendorsOrderList.vendorEmail,
                                vendorsOrderList.createdAt,
                                vendorsOrderList.totalPrice,
                                vendorsOrderList.vendorsOrderListStatus
                        )
                )
                .from(vendorsOrderList)
                .join(vendorsOrderList.delivery, delivery)
                .where(
                        ltGroupId(groupId),
                        vendorsOrderList.userEmail.eq(userEmail),
                        groupId != null ? vendorsOrderList.groupId.loe(groupId) : null,
                        vendorsOrderList.orderDeleteStatus.eq(1)
                )
                .orderBy(vendorsOrderList.groupId.desc(), vendorsOrderList.id.desc())
                .limit(10)
                .fetch();

        log.info("results: {}", results);
        // 연관된 OrderDetail 엔터티를 가져와서 DTO에 설정 및 나머지 로직 처리
        results.forEach(result -> {
            // 하나씩 builder 패턴으로 생성해서 넣어줘야 함
            List<OrderDetail> orderDetails = orderDetailRepository.findByVendorsOrderListId(result.getGroupId());
            List<OrderDetailOutResponseDto> orderDetailOutResponseDtoList = new ArrayList<>();
            orderDetails.forEach(orderDetail -> {
                OrderDetailOutResponseDto orderDetailOutResponseDto = OrderDetailOutResponseDto.builder()
                        .productId(orderDetail.getProductId())
                        .productName(orderDetail.getProductName())
                        .productImageUrl(orderDetail.getProductImageUrl())
                        .productPrice(orderDetail.getProductPrice())
                        .productSize(orderDetail.getProductSize())
                        .productColor(orderDetail.getProductColor())
                        .productStock(orderDetail.getProductStock())
                        .productDiscountRate(orderDetail.getProductDiscountRate())
                        .couponId(orderDetail.getCouponId())
                        .couponDiscountPrice(orderDetail.getCouponDiscountPrice())
                        .build();
                orderDetailOutResponseDtoList.add(orderDetailOutResponseDto);
            });
            result.setOrderDetailList(orderDetailOutResponseDtoList);
            result.updateProductImageUrl();
            result.updateProductNameAndTotalCount();
            result.updateVendorsOrderListStatusDescription();

        });

        return results;
    }
        /**
         * BooleanExpression을 사용하는 이유
         * 1) 가독성이 뛰어남
         * 2) null 반환 시 Where절에서 무시되기 때문에 안전
         * 3) and, or같은 메소드들을 이용해 BooleanExpression을 조합해 새로운 BooleanExpression를 만들 수 있어
         * 재사용성이 좋기 때문에 !
         *
         */

    // groupId가 null이 아니면 groupId보다 작은 값들만 조회
    private BooleanExpression ltGroupId(Long groupId){
        return groupId == null ? null : vendorsOrderList.groupId.lt(groupId);
    }

}