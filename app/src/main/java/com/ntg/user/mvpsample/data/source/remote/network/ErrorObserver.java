package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.base.ErrorCallback;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class ErrorObserver<T> implements Observer<T> {

    ErrorCallback errorCallback;

    public ErrorObserver(ErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof RetrofitException) {
            RetrofitException retrofitException = (RetrofitException) e;
            String errorMsg;
            switch (retrofitException.getType()) {
                case ErrorType.HTTP:
                    errorMsg = retrofitException.getMessage();
                    break;
                case ErrorType.NETWORK:
                    errorMsg = "Server Error: Check your connection";
                    break;
                default:
                    errorMsg = retrofitException.getMessage();
            }
            errorCallback.onError(errorMsg);
        }
    }

    @Override
    public void onComplete() {

    }
}
