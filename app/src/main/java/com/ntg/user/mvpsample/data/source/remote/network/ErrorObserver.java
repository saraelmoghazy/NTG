package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.ErrorCallback;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class ErrorObserver<T> implements Observer<T> {

    private BasePresenter basePresenter;

    public ErrorObserver(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

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
            basePresenter.onError(errorMsg);
        }
    }

    @Override
    public void onComplete() {

    }
}
