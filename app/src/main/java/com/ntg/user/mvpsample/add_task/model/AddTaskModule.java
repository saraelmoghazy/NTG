package com.ntg.user.mvpsample.add_task.model;

import com.ntg.user.mvpsample.network.remote.ForApplication;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sara Elmoghazy
 */
@Module
@ForApplication
public class AddTaskModule {

    @Provides
    RemoteAddTaskRepo provideRemoteAddTaskRepo(RetrofitProvider retrofitProvider) {
        return RemoteAddTaskRepo.getInstance(retrofitProvider);
    }

    @Provides
    AddTaskRepository provideAddTaskRepo(RemoteAddTaskRepo remoteAddTaskRepo) {
        return AddTaskRepository.getInstance(remoteAddTaskRepo);
    }
}
