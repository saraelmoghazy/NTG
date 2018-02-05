//package com.ntg.user.mvpsample.data.source.remote.network;
//
//import android.support.annotation.NonNull;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Type;
//
//import io.reactivex.Observable;
//import retrofit2.Call;
//import retrofit2.CallAdapter;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//
///**
// * Created by ilias on 05/02/2018.
// */
//
//public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
//
//    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;
//
//    public RxErrorHandlingCallAdapterFactory() {
//        rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
//    }
//
//    @Override
//    public CallAdapter<?, ?> get(@NonNull Type returnType,
//                                 @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
//        return new ErrorHandlingRxCallAdapter(rxJava2CallAdapterFactory.get(returnType,
//                annotations, retrofit));
//    }
//
//    static class ErrorHandlingRxCallAdapter<> implements CallAdapter<?, Observable<?>>{
//
//        private CallAdapter<?, Observable<?>> callAdapter;
//
//        public ErrorHandlingRxCallAdapter(CallAdapter<R, Observable<R>> callAdapter) {
//            this.callAdapter = callAdapter;
//        }
//
//        @Override
//        public Type responseType() {
//            return callAdapter.responseType();
//        }
//
//        @Override
//        public Observable<R> adapt(@NonNull Call<R> call) {
//            return callAdapter.adapt(call).onErrorResumeNext(throwable -> {
//
//                return Observable.error(
//                        RetrofitExceptionConverter.convertToRetrofitException(throwable));
//            }) ;
//        }
//    }
//}
