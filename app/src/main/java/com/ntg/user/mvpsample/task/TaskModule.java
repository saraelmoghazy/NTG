package com.ntg.user.mvpsample.task;

import com.ntg.user.mvpsample.data.sourse.TasksRepository;
import com.ntg.user.mvpsample.data.sourse.remote.RetrofitProvider;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 2/8/2018.
 */
@Module
public class TaskModule {

    @Provides
    RemoteTaskRepo provideRemoteTaskRepo(RetrofitProvider retrofitProvider){
        return  RemoteTaskRepo.getInstance(retrofitProvider);
    }

    @Provides
    @Singleton
    TasksRepository provideTasksRepository(RemoteTaskRepo remoteTaskRepo){
        return TasksRepository.newInstance(remoteTaskRepo);
    }
}
