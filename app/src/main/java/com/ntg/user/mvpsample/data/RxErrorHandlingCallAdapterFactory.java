package com.ntg.user.mvpsample.data;

import com.ntg.user.mvpsample.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by islam on 2/6/2018.
 */

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    public RxErrorHandlingCallAdapterFactory(RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        this.rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxErrorHandlingCallAdapterFactory(rxJava2CallAdapterFactory.get(returnType
                , annotations , retrofit));
    }

    private class RxErrorHandlingCallAdapter implements CallAdapter<io.reactivex.android.R , Observable<R>>{

        @Override
        public Type responseType() {
            return null;
        }

        @Override
        public Observable<R> adapt(Call<R> call) {
            return null;
        }
    }
}
