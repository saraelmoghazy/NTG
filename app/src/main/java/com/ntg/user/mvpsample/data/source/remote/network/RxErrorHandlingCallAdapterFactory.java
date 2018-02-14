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
    private final Retrofit retrofit;

    private RxErrorHandlingCallAdapterFactory(Retrofit retrofit) {
        rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
        this.retrofit = retrofit;
    }

    public static CallAdapter.Factory create(Retrofit retrofit) {
        return new RxErrorHandlingCallAdapterFactory(retrofit);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType,
                                 @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        return new ErrorHandlingRxCallAdapter(rxJava2CallAdapterFactory.get(returnType,
                annotations, retrofit),retrofit);
    }

    static class ErrorHandlingRxCallAdapter<T> implements CallAdapter<T, Observable<T>> {

        private CallAdapter<T, Observable<T>> callAdapter;
        Retrofit retrofit;

        ErrorHandlingRxCallAdapter(CallAdapter<T, Observable<T>> callAdapter, Retrofit retrofit) {
            this.callAdapter = callAdapter;
            this.retrofit = retrofit;
        }

        @Override
        public Type responseType() {
            return callAdapter.responseType();
        }

        @Override
        public Observable<T> adapt(@NonNull Call<T> call) {
            return callAdapter.adapt(call).onErrorResumeNext(throwable -> {

                return Observable.error(
                        RetrofitExceptionConverter.convertToRetrofitException(throwable,retrofit));
            });
        }
    }
}
