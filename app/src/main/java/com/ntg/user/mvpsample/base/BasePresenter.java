package com.ntg.user.mvpsample.base;

import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.network.remote.ErrorType;
import com.ntg.user.mvpsample.network.remote.RetrofitException;

import java.lang.ref.WeakReference;

/**
 * Base Presenter to handle view attachment and define common
 * behavior across all presenters in the application.
 *
 * @author Sara Elmoghazy
 */
public class BasePresenter<V extends BaseView> {

    private WeakReference<V> viewRef;

    public BasePresenter(V view) {
        attachView(view);
    }

    private void attachView(@NonNull V view) {
        viewRef = new WeakReference<>(view);
    }

    protected V getView() {
        return viewRef.get();
    }

    public boolean isViewAttached() {
        return viewRef != null && getView() != null;
    }

    public void start() {

    }

    public void showLoadingIndicator() {
        if (isViewAttached()) {
            getView().showLoadingIndicator();
        }
    }

    public void hideLoadingIndicator() {
        if (isViewAttached()) {
            getView().hideLoadingIndicator();
        }
    }

    public void handelError(RetrofitException retrofitException) {
        if (retrofitException.getErrorType() == ErrorType.HTTP)
            getView().showErrorMessage(retrofitException.getMessage());
        else
            getView().showNoInternetConnectionMessage();
    }

}
