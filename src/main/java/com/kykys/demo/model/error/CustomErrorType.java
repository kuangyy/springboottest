package com.kykys.demo.model.error;

/**
 * Created by kuangye on 2017/4/24.
 */
public class CustomErrorType {

    private int code;

    private String message;


    public CustomErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomErrorType() {
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
