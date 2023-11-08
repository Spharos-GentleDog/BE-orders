package egenius.orders.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(HttpStatus.OK,true, 200, "요청에 성공하였습니다."),

    /**
     * 900: 기타 에러
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 900, "Internal server error"),

    /**
     * 1000 : Order Service Error
     */
    ALREADY_PAID_ORDER_ID(HttpStatus.BAD_REQUEST, false, 1000, "이미 결제된 주문번호입니다"),
    DOSE_NOT_EXIST_PAYMENT(HttpStatus.BAD_REQUEST, false, 1001, "결제내역이 존재하지 않습니다"),
    CANCELED_AMOUNT_EXCEEDED(HttpStatus.BAD_REQUEST, false, 1002, "취소 금액 한도를 초과하였습니다"),
    PAYMENT_DATA_TRANSFER_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, false, 1003, "결제 정산 정보 전송에 실패하였습니다")
    ;


    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private String message;

}
