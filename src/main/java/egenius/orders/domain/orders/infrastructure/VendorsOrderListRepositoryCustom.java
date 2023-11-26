package egenius.orders.domain.orders.infrastructure;

import egenius.orders.domain.orders.dto.out.users.VendorsOrderSummaryOutResponseDto;

import java.util.List;

public interface VendorsOrderListRepositoryCustom {

    List<VendorsOrderSummaryOutResponseDto> findByFilter(String userEmail, Long groupId);
}
