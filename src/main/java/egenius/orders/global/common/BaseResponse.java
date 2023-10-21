package egenius.orders.global.common;


import static egenius.orders.global.common.BaseResponseStatus.SUCCESS;

public record BaseResponse<T>(Boolean isSuccess, String message, int code, T result) {
    // 요청 성공한 경우
    public BaseResponse(T result) {
        this(true, SUCCESS.getMessage(), SUCCESS.getCode(), result);
    }

    // 요청 실패한 경우
    public BaseResponse(BaseResponseStatus status) {
        this(false, status.getMessage(), status.getCode(), null);
    }
}
