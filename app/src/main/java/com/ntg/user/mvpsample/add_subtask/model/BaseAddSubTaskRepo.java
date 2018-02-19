package com.ntg.user.mvpsample.add_subtask.model;

import com.ntg.user.mvpsample.base.BaseObservable;
import com.ntg.user.mvpsample.network.SubTask;
import com.ntg.user.mvpsample.network.remote.APIEndPoint;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import io.reactivex.Observable;

/**
 * @author islam
 */

public class BaseAddSubTaskRepo extends BaseObservable implements AddSubTaskDataSource {

    private static BaseAddSubTaskRepo INSTANCE = null;
    RetrofitProvider retrofitProvider;

    private BaseAddSubTaskRepo(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
    }

    public static BaseAddSubTaskRepo getInstance(RetrofitProvider retrofitProvider) {
        if (INSTANCE == null)
            INSTANCE = new BaseAddSubTaskRepo(retrofitProvider);

        return INSTANCE;
    }

    @Override
    public Observable<SubTask> saveSubTask(String id, SubTask subTasks) {
        APIEndPoint APIEndPoint = retrofitProvider.getRetrofit().create(APIEndPoint.class);
        return getObservable(APIEndPoint.saveSubTask(id, subTasks));
    }
}
