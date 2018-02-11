package com.ntg.user.mvpsample.data;

import android.accounts.NetworkErrorException;
import com.ntg.user.mvpsample.ApiError;
import com.ntg.user.mvpsample.R;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
 * @author islam
 */

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;
    private Retrofit retrofit;

    public RxErrorHandlingCallAdapterFactory(Retrofit retrofit) {
        this.rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
        this.retrofit = retrofit;
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxErrorHandlingCallAdapter(rxJava2CallAdapterFactory.get(returnType
                , annotations, retrofit));
    }

    private class RxErrorHandlingCallAdapter implements CallAdapter<R, io.reactivex.Observable<R>> {
        private CallAdapter wrapCallAdapter;

        public RxErrorHandlingCallAdapter(CallAdapter wrapped) {
            wrapCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapCallAdapter.responseType();
        }


        @Override
        public io.reactivex.Observable<R> adapt(Call call) {
            return ((io.reactivex.Observable<R>) wrapCallAdapter.adapt(call))
                    .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends R>>() {
                        @Override
                        public ObservableSource<? extends R> apply(Throwable throwable) throws Exception {
                            return io.reactivex.Observable.error(covertError(throwable));
                        }
                    });
        }

        public ApiError covertError(Throwable t) {
            ApiError error = null;
            if (t instanceof HttpException) {
                HttpException httpException = (HttpException) t;
                Converter<ResponseBody, ApiError> converter = retrofit
                        .responseBodyConverter(ApiError.class, new Annotation[0]);
                ResponseBody responseBody = httpException.response().errorBody();
                if (responseBody != null) {
                    try {
                        error = converter.convert(responseBody);
                    } catch (IOException e) {
                    }
                    return error;
                } else {
                    return null;
                }
            } else if (t instanceof NetworkErrorException) {
                return new ApiError(-1, "otherException", 2);
            }

            return error;
        }
    }
}