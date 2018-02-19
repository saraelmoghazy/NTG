package com.ntg.user.mvpsample.base;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sara Elmoghazy on 19/02/2018.
 */

public class BaseObservable<M> {

    private final Scheduler subscribeOnThread;
    private final Scheduler postExecutionThread;

    public BaseObservable() {
        this.subscribeOnThread = Schedulers.newThread();
        this.postExecutionThread = AndroidSchedulers.mainThread();
    }

    public Observable<M> getObservable(Observable<M> observable) {
        return observable.subscribeOn(subscribeOnThread).observeOn(postExecutionThread);
    }
}
