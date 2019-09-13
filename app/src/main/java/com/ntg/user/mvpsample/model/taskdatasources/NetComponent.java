package com.ntg.user.mvpsample.model.taskdatasources;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by devsaad on 2/8/2018.
 */

@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {

    void inject(TasksRemoteDataSource remoteDataSource);


    class Intializer {
        private static NetComponent netComponent;

        public static NetComponent buildComponent() {

            netComponent = DaggerNetComponent.builder().netModule(new NetModule()).build();
            return netComponent;
        }
    }

}
