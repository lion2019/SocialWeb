package com.social.exception;

/**
 * 定義 返回碼和訊息
 */
public enum ResponseEnum implements IResponseEnum {
	
	OK(0,"success"),
	
	// common 
	parameter_empty(1, "參數 為空"),
	
	// user parameter 錯誤訊息，建議用英文
	Illegal_id(100, "id 不合法"),
	name_is_empty(101, "姓名 不可為空"),
	password_is_empty(102, "密碼 不可為空"),
	Illegal_email(103, "email 不合法"),
	phone_is_empty(104, "手機 不可為空"),
	address_is_empty(105, "地址 不可為空"),
	password_not_match(106, "密碼 不一致"),
	
	// user login
	user_not_found(300,"用戶不存在"),
	password_error(301,"密碼錯誤"),

	// friend
	friend_not_self(400, "好友不可為自己"),

	// sql insert error
	insert_error(201, "insert error!!"),
	delete_error(202, "delete error!!");
	
	
	ResponseEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
     * 返回碼
     */
    private int code;
    /**
     * 返回訊息
     */
    private String message;
    
	@Override
	public int getCode() {
		return code;
	}
	@Override
	public String getMessage() {
		return message;
	}

}
