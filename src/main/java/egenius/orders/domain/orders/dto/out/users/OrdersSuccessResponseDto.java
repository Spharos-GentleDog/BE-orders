package egenius.orders.domain.orders.dto.out.users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrdersSuccessResponseDto {

    private String orderNumber;

}
