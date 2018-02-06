package com.ntg.user.mvpsample.remote;

/**
 * Created by GM7 on 2/5/2018.
 */

public class ApiError{
    
    private int errorCode;
    private String errorMessage;
    
    public ApiError() {
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
}
