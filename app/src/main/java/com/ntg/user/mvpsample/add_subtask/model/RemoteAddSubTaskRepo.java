package com.ntg.user.mvpsample.add_subtask.model;

import com.ntg.user.mvpsample.base.BaseObservable;
import com.ntg.user.mvpsample.network.SubTask;
import com.ntg.user.mvpsample.network.remote.APIEndPoint;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public class RemoteAddSubTaskRepo extends BaseObservable implements AddSubTaskDataSource {

    private static RemoteAddSubTaskRepo INSTANCE = null;
    RetrofitProvider retrofitProvider;

    private RemoteAddSubTaskRepo(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
    }

    public static RemoteAddSubTaskRepo getInstance(RetrofitProvider retrofitProvider) {
        if (INSTANCE == null)
            INSTANCE = new RemoteAddSubTaskRepo(retrofitProvider);

        return INSTANCE;
    }

    @Override
    public Observable<SubTask> saveSubTask(int id, SubTask subTasks) {
        APIEndPoint APIEndPoint = retrofitProvider.getRetrofit().create(APIEndPoint.class);
        return getObservable(APIEndPoint.saveSubTask(id, subTasks));
    }
}
