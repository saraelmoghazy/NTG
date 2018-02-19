package com.ntg.user.mvpsample.add_subtask.model;

import com.ntg.user.mvpsample.add_subtask.presenter.SubTaskPresenter;
import com.ntg.user.mvpsample.add_task.model.AddTaskModule;
import com.ntg.user.mvpsample.add_task.model.DaggerAddTaskComponent;
import com.ntg.user.mvpsample.add_task.presenter.AddTaskPresenter;
import com.ntg.user.mvpsample.network.remote.NetComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */
@Singleton
@Component(dependencies = {NetComponent.class}, modules = {AddSubTaskModule.class})
public interface AddSubTaskComponent {

    void inject(SubTaskPresenter taskPresenter);

    class Initializer {

        private static AddSubTaskComponent component;

        public static AddSubTaskComponent buildComponent() {
            if (component == null) {
                component = DaggerAddSubTaskComponent.builder()
                        .addSubTaskModule(new AddSubTaskModule())
                        .netComponent(NetComponent.Initializer.buildComponent())
                        .build();
            }

            return component;
        }
    }
}
