package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.data.source.TasksDataSource;
import com.ntg.user.mvpsample.data.source.remote.ForApplication;
import com.ntg.user.mvpsample.data.source.remote.TasksRemoteDataSource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ilias on 07/02/2018.
 */

@ForApplication
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    RetrofitProvider getRetrofitProvider();

    class Initializer {
        public static NetworkComponent getNetworkComponent() {
            return DaggerNetworkComponent.builder()
                    .networkModule(new NetworkModule())
                    .build();
        }
    }
}
