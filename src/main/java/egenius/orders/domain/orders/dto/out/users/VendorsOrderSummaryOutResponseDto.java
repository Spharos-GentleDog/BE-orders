package egenius.orders.domain.orders.dto.out.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import egenius.orders.domain.orders.entity.enums.VendorsOrderListStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VendorsOrderSummaryOutResponseDto {

    private Long groupId;
    private String orderNumber;
    private String brandName;
    private String brandLogoImageUrl;
    private String vendorEmail;
    private Integer totalPrice;
    private String productNameAndTotalCount;
    private VendorsOrderListStatus vendorsOrderListStatus;
    private String vendorsOrderListStatusDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @Builder.Default
    private List<OrderDetailOutResponseDto> orderDetailList = new ArrayList<>();

    // vendorsOrderListStatusDescription 필드를 업데이트합니다.
    public void updateVendorsOrderListStatusDescription() {
        this.vendorsOrderListStatusDescription = this.vendorsOrderListStatus.getDescription();
    }

    // orderDetailList에서 첫 상품명과 List<OrderDetail>에서의 상품 수량을 합쳐서 productNameAndTotalCount 필드를 업데이트합니다.
    public void updateProductNameAndTotalCount() {
        this.productNameAndTotalCount =
                orderDetailList.get(0).getProductName() + " 외 " + (orderDetailList.size() - 1) + "개";
    }

}
