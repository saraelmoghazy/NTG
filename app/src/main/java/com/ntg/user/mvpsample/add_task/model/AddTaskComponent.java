package com.ntg.user.mvpsample.add_task.model;

import com.ntg.user.mvpsample.add_task.presenter.AddTaskPresenter;
import com.ntg.user.mvpsample.network.remote.NetComponent;
import com.ntg.user.mvpsample.tasks.presenter.TaskPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */
@Singleton
@Component(dependencies = {NetComponent.class}, modules = {AddTaskModule.class})
public interface AddTaskComponent {

    void inject(AddTaskPresenter taskPresenter);

    class Initializer {

        private static AddTaskComponent component;

        public static AddTaskComponent buildComponent() {
            if (component == null) {
                component = DaggerAddTaskComponent.builder()
                        .addTaskModule(new AddTaskModule())
                        .netComponent(NetComponent.Initializer.buildComponent())
                        .build();
            }

            return component;
        }
    }
}
