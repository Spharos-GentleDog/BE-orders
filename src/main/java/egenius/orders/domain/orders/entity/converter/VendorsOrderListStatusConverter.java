package egenius.orders.domain.orders.entity.converter;

import egenius.orders.domain.orders.entity.enums.VendorsOrderListStatus;
import egenius.orders.global.common.enums.BaseEnumConverter;

public class VendorsOrderListStatusConverter extends BaseEnumConverter<VendorsOrderListStatus, Integer, String> {
    public VendorsOrderListStatusConverter() {
        super(VendorsOrderListStatus.class);
    }
}
