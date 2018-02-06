package com.ntg.user.mvpsample.data.source.remote;

import android.util.Log;

import com.ntg.user.mvpsample.R;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by mohamed yassin on 2/5/2018.
 */

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    public RxErrorHandlingCallAdapterFactory() {
        this.rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
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
        public io.reactivex.Observable<R> adapt(Call<R> call) {
            return ((io.reactivex.Observable) wrapCallAdapter.adapt(call))
                    .onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                        @Override
                        public ObservableSource apply(Throwable throwable) throws Exception {
                            return io.reactivex.Observable.error(parseError(throwable));
                        }
                    });
        }
        public  RetrofitException parseError(Throwable t) {
            RetrofitException retrofitException =null;
            if (t instanceof IOException) {
                IOException ioException = (IOException) t;
                retrofitException = new RetrofitException(-1, ioException.getMessage(),
                        ErrorType.NETWORK);
            }else if(t instanceof HttpException){
                HttpException httpException = (HttpException) t;
                Converter<ResponseBody, ApiError> converter = ApiClient.getClient()
                        .responseBodyConverter(RetrofitException.class, new Annotation[0]);
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
}
