package com.ntg.user.mvpsample;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.ntg.user.mvpsample.data.source.remote.error_handeling.RetrofitException;
import com.ntg.user.mvpsample.model.taskdatasources.TasksAPI;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by devsaad on 2/6/2018.
 */

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    public RxErrorHandlingCallAdapterFactory() {
        this.rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxErrorHandlingCallAdapter(rxJava2CallAdapterFactory.get(returnType,annotations,retrofit));
    }

    private class RxErrorHandlingCallAdapter implements CallAdapter< R , Observable<R>>{
        private CallAdapter wrapCallAdapter;

        public RxErrorHandlingCallAdapter(CallAdapter wrapped) {
            wrapCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapCallAdapter.responseType();
        }

        @Override
        public Observable<R> adapt(Call<R> call) {
            return ((Observable)wrapCallAdapter.adapt(call))
                    .onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                        @Override
                        public ObservableSource apply(Throwable throwable) throws Exception {
                            return Observable.error(convertToRetrofitException(throwable));
                        }
                    });
        }
    }

    private RetrofitException convertToRetrofitException(Throwable throwable){
        RetrofitException retrofitException = null;
        if (throwable instanceof HttpException){
            HttpException httpException = (HttpException) throwable;
            Converter<ResponseBody , RetrofitException> converter = TasksAPI.getClient()
                    .responseBodyConverter(RetrofitException.class , new Annotation[0]);
            ResponseBody responseBody = httpException.response().errorBody();
                if (responseBody != null){
                    try {
                        retrofitException = converter.convert(responseBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return retrofitException;
                }else {
                    return null;
                }


        }else if(throwable instanceof NetworkErrorException) {
            return new RetrofitException(-1 , "errrrr" , -1);
        }
        return retrofitException;
    }
}
