package com.ntg.user.mvpsample.data.source.remote;

/**
 * Created by mohamed yassin on 2/5/2018.
 */

public class ApiError {
    private int code;
    private String message;

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
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
