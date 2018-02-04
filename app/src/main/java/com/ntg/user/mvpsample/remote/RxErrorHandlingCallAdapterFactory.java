package com.ntg.user.mvpsample.remote;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;



public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return null;
    }


    private class RxCallAdapterWrapper<R> implements CallAdapter<R, Observable<R>> {
        private final Retrofit retrofit;
        private final CallAdapter<R, ?> mWrappedCallAdapter;

        public RxCallAdapterWrapper(final Retrofit retrofit, final CallAdapter<R, ?> wrapped) {
            this.retrofit = retrofit;
            mWrappedCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrappedCallAdapter.responseType();
        }

        @Override
        public Observable<R> adapt(Call<R> call) {
            return ((Observable<R>) mWrappedCallAdapter.adapt(call)).onErrorResumeNext(
                    throwable -> {
                        return Observable.error(generateCustomError(throwable));
                    });
        }

        private APIError generateCustomError(final Throwable throwable) {
            final HttpException httpException = (HttpException) throwable;
            final Response response = httpException.response();
            if (response.code() == 400) {
                {
                    try {
                        Converter<ResponseBody, APIError> converter =
                                retrofit.responseBodyConverter(APIError.class, new Annotation[0]);
                        return converter.convert(response.errorBody());
                    } catch (IOException ex) {
                        return new APIError();
                    }
                }
            }
            return new APIError();
        }
    }
}
