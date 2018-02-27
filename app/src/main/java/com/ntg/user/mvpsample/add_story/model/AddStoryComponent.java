package com.ntg.user.mvpsample.add_story.model;

import com.ntg.user.mvpsample.add_story.presenter.AddStoryPresenter;
import com.ntg.user.mvpsample.network.remote.NetComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Sara Elmoghazy
 */
@Singleton
@Component(dependencies = {NetComponent.class}, modules = {AddStoryModule.class})
public interface
AddStoryComponent {

    void inject(AddStoryPresenter taskPresenter);

    class Initializer {

        private static AddStoryComponent component;

        public static AddStoryComponent buildComponent() {
            if (component == null) {
                component = DaggerAddStoryComponent.builder()
                        .addStoryModule(new AddStoryModule())
                        .netComponent(NetComponent.Initializer.buildComponent())
                        .build();
            }

            return component;
        }
    }
}
