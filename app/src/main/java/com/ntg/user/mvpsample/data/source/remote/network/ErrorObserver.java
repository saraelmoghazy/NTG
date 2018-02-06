package com.ntg.user.mvpsample.data.source.remote.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class ErrorObserver<T> implements BaseObserver<T> {

    private static String errorMsg;

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof RetrofitException) {
            RetrofitException retrofitException = (RetrofitException) e;
            switch (retrofitException.getType()) {
                case ErrorType.HTTP:
                    errorMsg = retrofitException.getMessage();
                    break;
                case ErrorType.NETWORK:
                    errorMsg = retrofitException.getMessage();
                    break;
                default:
                    errorMsg = retrofitException.getMessage();
            }
            getErrorMsg(errorMsg);
        }
    }

    @Override
    public void onComplete() {

    }
}
