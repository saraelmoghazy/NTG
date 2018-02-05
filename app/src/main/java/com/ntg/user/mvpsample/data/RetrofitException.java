package com.ntg.user.mvpsample.data;

import android.support.annotation.IntDef;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

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