//package egenius.orders.domain.orders.dto.out;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.querydsl.core.annotations.QueryProjection;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Getter
//@Setter
//@Builder
//public class OrdersSummaryOutResponseDto {
//
//    private String ordersNumber;
//    private String brandName;
//    private String brandLogoImageUrl;
//    private String vendorEmail;
//    private Integer totalProductPrice;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime ordersDate;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime deliveryDate;
//    private String deliveryStatus;
//    private List<OrdersDetailOutResponseDto> ordersDetailList;
//
//    @QueryProjection
//    public OrdersSummaryOutResponseDto(String ordersNumber, String brandName, String brandLogoImageUrl, String vendorEmail, Integer totalProductPrice, LocalDateTime ordersDate, LocalDateTime deliveryDate, String deliveryStatus, List<OrdersDetailOutResponseDto> ordersDetailList) {
//        this.ordersNumber = ordersNumber;
//        this.brandName = brandName;
//        this.brandLogoImageUrl = brandLogoImageUrl;
//        this.vendorEmail = vendorEmail;
//        this.totalProductPrice = totalProductPrice;
//        this.ordersDate = ordersDate;
//        this.deliveryDate = deliveryDate;
//        this.deliveryStatus = deliveryStatus;
//        this.ordersDetailList = ordersDetailList;
//    }
//
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
//
//        @QueryProjection
//        public OrdersDetailOutResponseDto(Long productId, String productName, String productImageUrl, Integer productPrice, String productSize, String productColor, Integer productStock, Integer productOrdersStatus, Integer productDiscountRate) {
//            this.productId = productId;
//            this.productName = productName;
//            this.productImageUrl = productImageUrl;
//            this.productPrice = productPrice;
//            this.productSize = productSize;
//            this.productColor = productColor;
//            this.productStock = productStock;
//            this.productOrdersStatus = productOrdersStatus;
//            this.productDiscountRate = productDiscountRate;
//        }
//
//    }
//
//
//}
