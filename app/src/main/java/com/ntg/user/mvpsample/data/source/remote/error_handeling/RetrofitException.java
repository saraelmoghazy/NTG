package com.ntg.user.mvpsample.data.source.remote.error_handeling;

import com.ntg.user.mvpsample.model.ErrorType;

/**
 * Created by devsaad on 2/5/2018.
 */

public class RetrofitException extends RuntimeException{
    private int errorCode;
    private String errorMessage;
    private int errorType;

    public RetrofitException(int errorCode, String errorMessage, int errorType) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(@ErrorType int errorType) {
        this.errorType = errorType;
    }
}

