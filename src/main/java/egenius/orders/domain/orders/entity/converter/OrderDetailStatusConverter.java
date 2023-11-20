package egenius.orders.domain.orders.entity.converter;

import egenius.orders.domain.orders.entity.enums.OrderDetailStatus;
import egenius.orders.global.common.enums.BaseEnumConverter;

public class OrderDetailStatusConverter extends BaseEnumConverter<OrderDetailStatus, Integer, String> {

    public OrderDetailStatusConverter() {super(OrderDetailStatus.class);}
}
