package com.social.exception;

/**
 * 驗証的錯誤類
 * 接收請求參數後，驗証失敗時使用
 * 在 XXXRequest 裡實作驗証邏輯
 */
public class ValidException extends BaseException {
    public ValidException() {
    }
    public ValidException(String message) {
        super(message);
    }
    public ValidException(String message, Throwable cause) {
        super(message, cause);
    }
    public ValidException(IResponseEnum responseEnum, Throwable cause) {
        super(responseEnum, cause);
    }
    public ValidException(IResponseEnum responseEnum) {
        super(responseEnum);
    }
}
