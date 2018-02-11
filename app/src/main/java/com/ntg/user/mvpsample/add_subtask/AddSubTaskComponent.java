package com.ntg.user.mvpsample.add_subtask;

import com.ntg.user.mvpsample.add_tasks.AddTaskComponent;
import com.ntg.user.mvpsample.add_tasks.AddTaskModule;
import com.ntg.user.mvpsample.add_tasks.DaggerAddTaskComponent;
import com.ntg.user.mvpsample.data.sourse.remote.NetComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by islam on 2/8/2018.
 */
@Singleton
@Component(dependencies = {NetComponent.class} , modules = {AddSubTaskModule.class})
public interface AddSubTaskComponent {

    void inject(SubTaskPresenter subTaskPresenter);

    class Initializer {

        private static AddSubTaskComponent component;

        public static AddSubTaskComponent buildComponent() {
            if (component == null) {
                component = DaggerAddSubTaskComponent
                        .builder().netComponent(NetComponent.Initializer.buildComponent())
                        .build();
                }
            return component;
        }
    }
}
