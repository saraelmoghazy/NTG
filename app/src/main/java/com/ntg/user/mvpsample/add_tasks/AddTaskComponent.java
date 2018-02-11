package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.data.sourse.remote.NetComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by islam on 2/8/2018.
 */
@Singleton
@Component(dependencies = {NetComponent.class} , modules = {AddTaskModule.class})
public interface AddTaskComponent {

    void inject(AddTaskPresenter addTaskPresenter);

    class Initializer {

        private static AddTaskComponent component;

        public static AddTaskComponent buildComponent() {
            if (component == null) {
                component = DaggerAddTaskComponent
                        .builder().netComponent(NetComponent.Initializer.buildComponent())
                        .build();
            }
            return component;
        }
    }
}
