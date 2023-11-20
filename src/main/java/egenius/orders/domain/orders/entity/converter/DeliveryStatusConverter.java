package egenius.orders.domain.orders.entity.converter;

import egenius.orders.domain.orders.entity.enums.DeliveryStatus;
import egenius.orders.global.common.enums.BaseEnumConverter;

public class DeliveryStatusConverter extends BaseEnumConverter<DeliveryStatus, Integer, String> {

    public DeliveryStatusConverter() {
        super(DeliveryStatus.class);
    }
}
