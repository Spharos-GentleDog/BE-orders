package egenius.orders.domain.orders.webdto.out.vendors;

import com.fasterxml.jackson.annotation.JsonFormat;
import egenius.orders.domain.orders.dto.out.users.OrderDetailOutResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class VendorsOrderInfoOutWebDto {

    private Long VendorsOrderId;
    private String orderNumber;
    private String brandName;
    private String brandLogoImageUrl;
    private String vendorEmail;
    private Integer totalPrice;
    private String productNameAndTotalCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @Builder.Default
    private List<OrderDetailOutResponseDto> orderDetailList = new ArrayList<>();
}
