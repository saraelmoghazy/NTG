package com.ntg.user.mvpsample.data.sourse.remote;

import com.ntg.user.mvpsample.data.RxErrorHandlingCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sara Elmoghazy on 07/02/2018.
 */
@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(RemoteTaskRepo remoteTaskRepo);
    void inject(AddSubTaskRepo addSubTaskRepo);
    void inject(AddTaskRepo addTaskRepo);

    class Initializer {

        private static NetComponent component;

        public static NetComponent buildComponent() {
            if (component == null) {
                component = DaggerNetComponent.builder()
                        .netModule(new NetModule("http://twilight-grass-3888.getsandbox.com/"))
                        .build();
            }

            return component;
        }
    }
}
