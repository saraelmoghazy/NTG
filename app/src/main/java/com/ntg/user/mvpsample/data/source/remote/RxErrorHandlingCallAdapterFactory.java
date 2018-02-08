package com.ntg.user.mvpsample.data.source.remote;

import android.support.annotation.NonNull;
import com.ntg.user.mvpsample.R;
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
 * Created by mohamed yassin on 2/5/2018.
 */

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    Retrofit retrofit;

    public RxErrorHandlingCallAdapterFactory(Retrofit retrofit) {
        rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();

    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxErrorHandlingCallAdapter(rxJava2CallAdapterFactory.get(returnType, annotations, retrofit));
    }

    private class RxErrorHandlingCallAdapter implements CallAdapter<R, Observable<R>> {
        private CallAdapter mWrapCallAdapter;

        public RxErrorHandlingCallAdapter(CallAdapter wrapped) {
            mWrapCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrapCallAdapter.responseType();
        }

        @Override
        public Observable<R> adapt(Call call) {
            return ((Observable<R>) mWrapCallAdapter.adapt(call)).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends R>>() {
                @Override
                public ObservableSource<? extends R> apply(@NonNull Throwable throwable) throws Exception {
                    return Observable.error(asRetrofitException(throwable));
                }
            });
        }

        private RetrofitException asRetrofitException(final Throwable throwable) {
            RetrofitException retrofitException = null;
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                try {
                    Converter<ResponseBody, ApiError> converter = retrofit
                            .responseBodyConverter(ApiError.class, new Annotation[0]);
                    ApiError apiError = converter.convert(httpException.response().errorBody());
                    retrofitException = new RetrofitException(apiError.getCode(),
                            apiError.getMessage(), ErrorType.HTTP);
                } catch (Exception e) {
                }
            }
            else if (throwable instanceof IOException) {
                retrofitException = new RetrofitException(-1, throwable.getMessage(), ErrorType.NETWORK);
            } else {
                retrofitException = new RetrofitException(-1, throwable.getMessage(), ErrorType.UNEXPECTED);
            }

            return retrofitException;
        }

    }
}
