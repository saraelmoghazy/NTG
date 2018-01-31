package com.ntg.user.mvpsample.remote;

public class APIError extends RuntimeException {

    private int statusCode;
    private String messageErr;

    public APIError() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageErr() {
        return messageErr;
    }

    public void setMessageErr(String messageErr) {
        this.messageErr = messageErr;
    }
}
