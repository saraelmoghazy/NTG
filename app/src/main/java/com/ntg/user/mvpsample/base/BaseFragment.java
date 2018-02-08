package com.ntg.user.mvpsample.base;

import android.support.v4.app.Fragment;

/**
 * Created by ilias on 07/02/2018.
 */

public abstract class BaseFragment<T> extends Fragment implements IView<T> {

    @Override
    public void showErrorMsg(String errorMsg) {

    }
}
