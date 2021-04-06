package com.social.exception;

public class UserException extends BaseException {
    public UserException() {
    }
    public UserException(String message) {
        super(message);
    }
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserException(IResponseEnum responseEnum, Throwable cause) {
		super(responseEnum, cause);
	}
	public UserException(IResponseEnum responseEnum) {
		super(responseEnum);
	}
}
