package egenius.orders.domain.orders.entity;

import egenius.orders.global.common.enums.BaseEnum;
import egenius.orders.global.common.enums.BaseEnumConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // BaseEnum의 method를 impl하는것을 -> Getter로 대체
@AllArgsConstructor // 생성자를 직접 만들지 않고 -> AllArgs로 대체
public enum RefundStatus implements BaseEnum<Integer, String> {

    /**
     * 1. 코드 작성
     * 2. field 선언
     * 3. converter 구현
     */

    // 1. 코드 작성
    READY(0, "환불 준비중"),
    IN_PROGRESS(1, "환불 중"),
    DONE(2, "환불 완료"),
    CANCELED(3, "환불 취소");

    // 2. field 선언
    private final Integer code;
    private final String description;

    // 3. converter 구현
    @Converter(autoApply = true)
    static class thisConverter extends BaseEnumConverter<RefundStatus, Integer, String> {
        public thisConverter() {
            super(RefundStatus.class);
        }
    }
}
