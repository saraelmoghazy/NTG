package com.ntg.user.mvpsample.tasks.model;

import com.ntg.user.mvpsample.network.remote.NetComponent;
import com.ntg.user.mvpsample.tasks.presenter.TaskPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */
@Singleton
@Component(dependencies = {NetComponent.class}, modules = {GetTasksModule.class})
public interface GetTasksComponent {

    void inject(TaskPresenter taskPresenter);

    class Initializer {

        private static GetTasksComponent component;

        public static GetTasksComponent buildComponent() {
            if (component == null) {
                component = DaggerGetTasksComponent.builder()
                        .getTasksModule(new GetTasksModule())
                        .netComponent(NetComponent.Initializer.buildComponent())
                        .build();
            }

            return component;
        }
    }
}
