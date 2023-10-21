package egenius.orders.global.common;

import egenius.orders.global.common.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends Exception{
    private BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }
}
