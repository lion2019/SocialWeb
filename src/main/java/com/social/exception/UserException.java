package com.social.exception;

/**
 * 會員有關的錯誤類
 * 會員註冊前端接收參數後驗証失敗時使用
 * 新增會員失敗時使用
 */
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
    public UserException(int code, String message) {
        super(code, message);
    }
}
