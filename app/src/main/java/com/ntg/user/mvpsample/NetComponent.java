package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.remote.TaskRemoteRepo;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by GM7 on 2/8/2018.
 */

@Singleton
@Component(modules = {ApiModule.class})
public interface NetComponent {
    void inject(TaskRemoteRepo remoteTaskRepo);

    class Initializer {

        private static NetComponent component;

        public static NetComponent buildComponent() {
            if (component == null) {

                component = DaggerNetComponent.builder()
                        .apiModule(new ApiModule("http://quiet-shape-7108.getsandbox.com/"))
                        .build();
            }

            return component;
        }
    }
}