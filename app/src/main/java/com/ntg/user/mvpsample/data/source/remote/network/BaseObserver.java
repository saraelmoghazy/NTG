package com.ntg.user.mvpsample.data.source.remote.network;

import io.reactivex.Observer;

/**
 * Created by ilias on 06/02/2018.
 */

public interface BaseObserver<T> extends Observer<T> {
    void getErrorMsg(String errorMsg);
}
