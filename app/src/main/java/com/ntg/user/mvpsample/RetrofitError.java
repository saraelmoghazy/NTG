package com.ntg.user.mvpsample;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.ntg.user.mvpsample.data.sourse.remote.ApiClient;
import com.ntg.user.mvpsample.data.sourse.remote.ApiInterface;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;

/**
 *@author islam
 */

public class RetrofitError {
    private static final String TAG = RetrofitError.class.getSimpleName();

    public static ApiError covertError(Throwable  t){
        ApiError error = new ApiError();
        if (t instanceof HttpException){
            HttpException httpException = (HttpException)t;
            Converter<ResponseBody , ApiError> converter = ApiClient.getClient()
                    .responseBodyConverter(ApiError.class , new Annotation[0]);
            ResponseBody responseBody = httpException.response().errorBody();
            if (responseBody != null){
                try {
                    error = converter.convert(responseBody);
                }catch (IOException e){
                    Log.e(TAG , "Converting Failed");
                }
                return error;
            }else {
                return null;
            }
        }else if(t instanceof NetworkErrorException){
            return new ApiError(-1,"otherException" ,2);
        }
        return error;
    }
}
