package com.ntg.user.mvpsample.task;

import com.ntg.user.mvpsample.data.sourse.remote.NetComponent;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by islam on 2/8/2018.
 */
@Singleton
@Component(dependencies = {NetComponent.class}, modules = TaskModule.class)
public interface TaskComponent {
    void inject(TaskPresenter taskPresenter);

    class Initializer {

        private static TaskComponent component;

        public static TaskComponent buildComponent() {
            if (component == null) {
                component = DaggerTaskComponent
                        .builder().netComponent(NetComponent.Initializer.buildComponent())
                        .build();
            }
            return component;
        }
    }
}
