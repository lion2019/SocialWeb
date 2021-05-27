package com.social.exception;

import javax.servlet.ServletException;

/**
 * 定義此系統基礎 Exception 格式
 */
public class BaseException extends ServletException {

	private static final long serialVersionUID = 1L;
	/** 返回碼 */
	private int code;

	public BaseException() {
    }
	public BaseException(String message) {
        super(message);
    }
	public BaseException(int code, String message) {
		super(message);
		this.code = code;
	}
	public BaseException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
	public BaseException(IResponseEnum responseEnum, Throwable cause) {
		super(responseEnum.getMessage(), cause);
		this.code = responseEnum.getCode();
	}
	public BaseException(IResponseEnum responseEnum) {
		super(responseEnum.getMessage());
		this.code = responseEnum.getCode();
	}
	
	public int getCode() {
		return code;
	}
}
