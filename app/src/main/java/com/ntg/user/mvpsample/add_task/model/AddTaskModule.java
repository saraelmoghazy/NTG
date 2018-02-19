package com.ntg.user.mvpsample.add_task.model;

import com.ntg.user.mvpsample.network.remote.ForApplication;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */
@Module
@ForApplication
public class AddTaskModule {

    @Provides
    RemoteAddTaskRepo provideRemoteTaskRepo(RetrofitProvider retrofitProvider) {
        return RemoteAddTaskRepo.getInstance(retrofitProvider);
    }

    @Provides
    AddTaskRepository provideTaskRepo(RemoteAddTaskRepo remoteTaskRepo) {
        return AddTaskRepository.getInstance(remoteTaskRepo);
    }
}
