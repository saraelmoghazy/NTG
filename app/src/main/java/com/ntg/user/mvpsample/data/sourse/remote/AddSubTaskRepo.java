package com.ntg.user.mvpsample.data.sourse.remote;

import android.util.Log;
import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author islam
 */

public class AddSubTaskRepo implements TasksDataSource.SaveSubTask {

    private static AddSubTaskRepo INSTANCE = null;
    @Inject
    RetrofitProvider retrofitProvider;

    public static AddSubTaskRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddSubTaskRepo();
            DaggerNetComponent.Initializer.buildComponent().inject(INSTANCE);
        }
        return INSTANCE;
    }


    @Override
    public void saveSubTask(String id, SubTask subTasks, AddSubTaskCallback addSubTaskCallback) {
        ApiInterface apiInterface = retrofitProvider.getRetrofit().create(ApiInterface.class);
        apiInterface.saveSubTask(id, subTasks).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubTask>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubTask subTask) {
                        addSubTaskCallback.onSubTaskAdded(subTask);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addSubTaskCallback.inSubTaskAddedFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
