package com.social.domain;

import com.social.exception.IResponseEnum;

public class BaseResponse {
    private int code;
    private String message;

    public BaseResponse(){
    }

    public BaseResponse(IResponseEnum resp){
        this.code = resp.getCode();
        this.message = resp.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
