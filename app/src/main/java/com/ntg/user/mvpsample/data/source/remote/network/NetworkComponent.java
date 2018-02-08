package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.data.source.TasksDataSource;
import com.ntg.user.mvpsample.data.source.remote.TasksRemoteDataSource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ilias on 07/02/2018.
 */

@Component(modules = {NetworkModule.class})
@Singleton
public interface NetworkComponent {
    void inject(TasksRemoteDataSource tasksRemoteDataSource);

    class Initializer {
        public static NetworkComponent getNetworkComponent(){
            return DaggerNetworkComponent.builder()
                    .networkModule(new NetworkModule())
                    .build();
        }
    }
}
