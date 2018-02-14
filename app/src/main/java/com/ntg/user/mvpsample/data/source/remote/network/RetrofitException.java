package com.ntg.user.mvpsample.data.source.remote.network;

/**
 * Created by ilias on 05/02/2018.
 */

public class RetrofitException extends RuntimeException {

    private int code;
    private String message;
    @ErrorType private int type;

    public RetrofitException(int code, String message,@ErrorType int type){
        this.code = code;
        this.message = message;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(@ErrorType int type) {
        this.type = type;
    }
}
