package com.ntg.user.mvpsample.data.source.remote;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;

/**
 * Created by mohamed yassin on 2/5/2018.
 */

//RetrofitExceptionConverter
public class ApiErrorUtil {
    private static final String TAG = ApiErrorUtil.class.getSimpleName();
    public static RetrofitException parseError(Throwable t) {
        RetrofitException retrofitException =null;
        if (t instanceof IOException) {
            IOException ioException = (IOException) t;
            retrofitException = new RetrofitException(-1, ioException.getMessage(),
                    ErrorType.NETWORK);
        }else if(t instanceof HttpException){
            HttpException httpException = (HttpException) t;
            Converter<ResponseBody, ApiError> converter = ApiClient.getClient()
                    .responseBodyConverter(ApiError.class, new Annotation[0]);
            ResponseBody responseErrorBody = httpException.response().errorBody();
            if (responseErrorBody != null) {
                try {
                    ApiError apiError = converter.convert(responseErrorBody);
                    retrofitException = new RetrofitException(apiError.getCode(),
                            apiError.getMessage(), ErrorType.HTTP);
                } catch (IOException e) {
                    Log.e(TAG, "HTTP Converting Failed");
                }
                return retrofitException;
            } else {
                return null;
            }


        }else {
            retrofitException = new RetrofitException(-3, t.getMessage(), ErrorType.UNEXPECTED);
        }
        return retrofitException;


    }
}
