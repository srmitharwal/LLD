package com.personal.designPattern.rateLimiter.models;

public class LimitResponse {
    String message;

    int code;

    public LimitResponse() {

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
