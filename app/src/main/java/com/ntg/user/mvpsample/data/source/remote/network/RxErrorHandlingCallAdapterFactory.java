package com.ntg.user.mvpsample.data.source.remote.network;

import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by ilias on 05/02/2018.
 */

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private final RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    private RxErrorHandlingCallAdapterFactory() {
        rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create(){
        return new RxErrorHandlingCallAdapterFactory();
    }

    @SuppressWarnings("unchecked")
    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType,
                                 @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        return new ErrorHandlingRxCallAdapter(rxJava2CallAdapterFactory.get(returnType,
                annotations, retrofit));
    }

    static class ErrorHandlingRxCallAdapter<T> implements CallAdapter<T, Observable<T>>{

        private CallAdapter<T, Observable<T>> callAdapter;

        public ErrorHandlingRxCallAdapter(CallAdapter<T, Observable<T>> callAdapter) {
            this.callAdapter = callAdapter;
        }

        @Override
        public Type responseType() {
            return callAdapter.responseType();
        }

        @Override
        public Observable<T> adapt(@NonNull Call<T> call) {
            return callAdapter.adapt(call).onErrorResumeNext(throwable -> {

                return Observable.error(
                        RetrofitExceptionConverter.convertToRetrofitException(throwable));
            }) ;
        }
    }
}
