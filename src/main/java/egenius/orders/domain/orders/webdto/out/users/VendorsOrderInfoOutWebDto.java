package egenius.orders.domain.orders.webdto.out.users;

import egenius.orders.domain.orders.dto.out.users.VendorsOrderSummaryOutResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class VendorsOrderInfoOutWebDto {

    private Long nextGroupId;
    private List<VendorsOrderSummaryOutResponseDto> vendorsOrderSummaryOutResponseDtoList;

}
