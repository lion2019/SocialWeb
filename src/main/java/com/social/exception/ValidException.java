package com.social.exception;

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
