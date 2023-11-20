package egenius.orders.domain.orders.entity.converter;


import egenius.orders.domain.orders.entity.enums.RefundAndExchangeStatus;
import egenius.orders.global.common.enums.BaseEnumConverter;

public class RefundAndExchangeStatusConverter extends BaseEnumConverter<RefundAndExchangeStatus, Integer, String> {

        public RefundAndExchangeStatusConverter() {
            super(RefundAndExchangeStatus.class);
        }

}
