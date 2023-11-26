package egenius.orders.domain.orders.dto.out.users;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailOutResponseDto {

    private Long productId;
    private String productName;
    private String productImageUrl;
    private Integer productPrice;
    private String productSize;
    private String productColor;
    private Integer productStock;
    private Integer productDiscountRate;
    private Long couponId;
    private Integer couponDiscountPrice;

}
