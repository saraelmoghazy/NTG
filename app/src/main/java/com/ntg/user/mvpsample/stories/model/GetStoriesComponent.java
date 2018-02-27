package com.ntg.user.mvpsample.stories.model;

import com.ntg.user.mvpsample.network.remote.NetComponent;
import com.ntg.user.mvpsample.stories.presenter.StoryPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Sara Elmoghazy
 */
@Singleton
@Component(dependencies = {NetComponent.class}, modules = {GetStoriesModule.class})
public interface GetStoriesComponent {

    void inject(StoryPresenter storyPresenter);

    class Initializer {

        private static GetStoriesComponent component;

        public static GetStoriesComponent buildComponent() {
            if (component == null) {
                component = DaggerGetStoriesComponent.builder()
                        .getStoriesModule(new GetStoriesModule())
                        .netComponent(NetComponent.Initializer.buildComponent())
                        .build();
            }

            return component;
        }
    }
}
