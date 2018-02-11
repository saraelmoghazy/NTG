package com.ntg.user.mvpsample.data.source;

import com.ntg.user.mvpsample.data.source.remote.TasksRemoteDataSource;
import com.ntg.user.mvpsample.data.source.remote.network.RetrofitProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ilias on 08/02/2018.
 */
@Singleton
@Module
class RepositoryModule {

    @Provides
    TasksRemoteDataSource provideRemoteTasksRepository(RetrofitProvider retrofitProvider){
        return TasksRemoteDataSource.getInstance(retrofitProvider.getRetrofit());
    }

    @Provides
    TasksRepository provideTasksRepository(TasksRemoteDataSource remoteDataSource){
        return TasksRepository.getInstance(remoteDataSource);
    }
}
