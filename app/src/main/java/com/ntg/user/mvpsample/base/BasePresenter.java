package com.ntg.user.mvpsample.base;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
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

    public void handelError(String message) {
        getView().showErrorMessage(message);
    }

}
