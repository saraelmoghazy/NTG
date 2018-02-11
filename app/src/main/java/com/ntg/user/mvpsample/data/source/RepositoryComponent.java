package com.ntg.user.mvpsample.data.source;

import com.ntg.user.mvpsample.add_edit_task.AddEditTaskPresenter;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.data.source.remote.ForApplication;
import com.ntg.user.mvpsample.data.source.remote.network.NetworkComponent;
import com.ntg.user.mvpsample.task_details.TaskDetailsPresenter;
import com.ntg.user.mvpsample.tasks.TasksPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ilias on 08/02/2018.
 */

@Singleton
@Component(dependencies = {NetworkComponent.class}, modules = {RepositoryModule.class})
public interface RepositoryComponent {

    void inject(TasksPresenter tasksPresenter);

    void inject(AddEditTaskPresenter addEditTaskPresenter);

    void inject(TaskDetailsPresenter taskDetailsPresenter);

    class Initializer {

        static RepositoryComponent repositoryComponent;

        public static RepositoryComponent getRepositoryComponent() {
            if (repositoryComponent == null) {
                repositoryComponent = DaggerRepositoryComponent.builder()
                        .networkComponent(NetworkComponent.Initializer.getNetworkComponent())
                        .build();
            }

            return repositoryComponent;
        }

    }
}
