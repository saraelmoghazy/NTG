package com.ntg.user.mvpsample.data.sourse.remote;

import android.util.Log;

import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by islam on 2/1/2018.
 */

public class AddSubTaskRepo implements TasksDataSource.SaveSubTask {

    private static AddSubTaskRepo INSTANCE = null;


    public static AddSubTaskRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddSubTaskRepo();
        }
        return INSTANCE;
    }


    @Override
    public void saveSubTask(String id, SubTask subTasks) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.saveSubTask(id, subTasks).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SubTask>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SubTask> subTasks) {
                        Log.i("lsit", subTasks.get(0).getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("err", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
