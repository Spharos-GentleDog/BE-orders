//package egenius.orders.domain.orders.dto.out;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Getter
//@Builder
//public class VendorOrdersOutResponseDto {
//
//    private Long ordersId;
//    private String ordersNumber;
//    private String userImageUrl;
//    private String userName;
//    private String userEmail;
//    private String ordersRequestMessage;
//
//    //( Server -> Client )
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime orderDate;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime deliveryDate;
//    private List<OrdersDetailOutResponseDto> ordersDetailList;
//
//    @Data
//    @Builder
//    public static class OrdersDetailOutResponseDto {
//        private Long productId;
//        private String productName;
//        private String productImageUrl;
//        private Integer productPrice;
//        private String productSize;
//        private String productColor;
//        private Integer productStock;
//        private Integer productOrdersStatus;
//        private Integer productDiscountRate;
//    }
//
//}
