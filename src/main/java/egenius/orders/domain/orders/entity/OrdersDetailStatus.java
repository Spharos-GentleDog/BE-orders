package egenius.orders.domain.orders.entity;

import egenius.orders.global.common.enums.BaseEnum;
import egenius.orders.global.common.enums.BaseEnumConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // BaseEnum의 method를 impl하는것을 -> Getter로 대체
@AllArgsConstructor // 생성자를 직접 만들지 않고 -> AllArgs로 대체
public enum OrdersDetailStatus implements BaseEnum<Integer, String> {

    /**
     * 1. 코드 작성
     * 2. field 선언
     * 3. converter 구현
     */

    // 1. 코드 작성
    READY(0, "주문 접수"),
    IN_PROGRESS(1, "주문 중"),
    DONE(2, "주문 완료"),
    CANCELED(3, "주문 취소"),
    EXCHANGE(4, "교환"),
    REFUND(5, "환불");

    // 2. field 선언
    private final Integer code;
    private final String description;

    // 3. converter 구현
    @Converter(autoApply = true)
    static class thisConverter extends BaseEnumConverter<OrdersDetailStatus, Integer, String> {
        public thisConverter() {
            super(OrdersDetailStatus.class);
        }
    }
}
