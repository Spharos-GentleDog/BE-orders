//package egenius.orders.domain.orders.infrastructure;
//
//import egenius.orders.domain.orders.dto.out.OrdersSummaryOutResponseDto;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//
//import java.time.LocalDate;
//
//public interface CustomOrdersDetailRepository {
//
//    // 1. 주문 요약 페이징 및 무한 스크롤
//    Slice<OrdersSummaryOutResponseDto> findByFilter(Long ordersId, String userEmail, Pageable pageable);
//
//
//}
