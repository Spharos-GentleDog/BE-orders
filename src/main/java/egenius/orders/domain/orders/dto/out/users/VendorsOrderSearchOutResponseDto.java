package egenius.orders.domain.orders.dto.out.users;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VendorsOrderSearchOutResponseDto {

    private Long nextGroupId;
    private Boolean hasNext;
    private List<VendorsOrderSummaryOutResponseDto> vendorsOrderSummaryOutResponseDtoList;

}
