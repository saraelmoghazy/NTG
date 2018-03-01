package com.ntg.user.mvpsample.base;

import com.ntg.user.mvpsample.network.remote.RetrofitException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Default {@link Observer} base class to be used whenever you want default error handling.
 *
 * @author Sara Elmoghazy
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
        presenter.hideLoadingIndicator();
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
