package com.ntg.user.mvpsample.network.remote;

/**
 * Created by Sara Elmoghazy on 04/02/2018.
 */

public class RetrofitException extends RuntimeException {

    private int code;
    private String message;
    private
    @ErrorType
    int errorType;

    public RetrofitException(int code, String message, @ErrorType int errorType) {
        this.code = code;
        this.message = message;
        this.errorType = errorType;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }
}