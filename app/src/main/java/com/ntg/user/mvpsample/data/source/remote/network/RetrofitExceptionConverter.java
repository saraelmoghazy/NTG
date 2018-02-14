package com.ntg.user.mvpsample.data.source.remote.network;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

/**
 * Created by ilias on 05/02/2018.
 */

class RetrofitExceptionConverter {

    private static final String TAG = RetrofitExceptionConverter.class.getSimpleName();


    static RetrofitException convertToRetrofitException(Throwable t, Retrofit retrofit) {
        RetrofitException retrofitException = null;
        if (t instanceof IOException) {
            IOException ioException = (IOException) t;
            retrofitException = new RetrofitException(-1, ioException.getMessage(),
                    ErrorType.NETWORK);
        } else if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            Converter<ResponseBody, TasksAPIError> converter =
                    retrofit.responseBodyConverter(TasksAPIError.class, new Annotation[0]);
            ResponseBody responseErrorBody = httpException.response().errorBody();
            if (responseErrorBody != null) {
                try {
                    TasksAPIError tasksAPIError = converter.convert(responseErrorBody);
                    retrofitException = new RetrofitException(tasksAPIError.getCode(),
                            tasksAPIError.getMessage(), ErrorType.HTTP);
                } catch (IOException e) {
                    Log.e(TAG, "HTTP Converting Failed");
                }
                return retrofitException;
            } else {
                return null;
            }
        } else {
            retrofitException = new RetrofitException(-3, t.getMessage(), ErrorType.UNEXPECTED);

        }
        return retrofitException;
    }
}
