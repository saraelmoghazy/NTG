package com.ntg.user.mvpsample;

/**
 * Created by islam on 2/5/2018.
 */

public class ApiError {
    private int statusCode;
    private String message;
    private int type;

    public ApiError() {
    }

    public ApiError(int statusCode, String message, int type) {
        this.statusCode = statusCode;
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatusCode() {

        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
