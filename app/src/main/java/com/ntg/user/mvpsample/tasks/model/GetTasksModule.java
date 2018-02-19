package com.ntg.user.mvpsample.tasks.model;

import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */
@Module
public class GetTasksModule {

    @Provides
    RemoteGetTasksRepo provideRemoteTaskRepo(RetrofitProvider retrofitProvider) {
        return RemoteGetTasksRepo.getInstance(retrofitProvider);
    }

    @Provides
    @Singleton
    GetTasksRepository provideTaskRepo(RemoteGetTasksRepo remoteRemoteGetTasksRepo) {
        return GetTasksRepository.getInstance(remoteRemoteGetTasksRepo);
    }
}
