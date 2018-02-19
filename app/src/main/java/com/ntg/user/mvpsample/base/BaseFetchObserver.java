package com.ntg.user.mvpsample.base;

import com.ntg.user.mvpsample.network.remote.ErrorType;
import com.ntg.user.mvpsample.network.remote.RetrofitException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */

public abstract class BaseFetchObserver<T> implements Observer<T> {
    BasePresenter presenter;

    public BaseFetchObserver(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }


    @Override
    public void onError(Throwable throwable) {
        RetrofitException retrofitException = null;
        if (throwable instanceof RetrofitException) {
            retrofitException = (RetrofitException) throwable;
        }
        presenter.handelError(retrofitException.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
