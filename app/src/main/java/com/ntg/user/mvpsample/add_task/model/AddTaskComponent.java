package com.ntg.user.mvpsample.add_task.model;

import com.ntg.user.mvpsample.add_task.presenter.TaskPresenter;
import com.ntg.user.mvpsample.network.remote.NetComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Sara Elmoghazy
 */
@Singleton
@Component(dependencies = {NetComponent.class}, modules = {AddTaskModule.class})
public interface AddTaskComponent {

    void inject(TaskPresenter taskPresenter);

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
