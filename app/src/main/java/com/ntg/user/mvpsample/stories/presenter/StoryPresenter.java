package com.ntg.user.mvpsample.stories.presenter;

import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.stories.model.GetStoriesComponent;
import com.ntg.user.mvpsample.stories.model.GetStoriesRepository;
import com.ntg.user.mvpsample.stories.view.StoriesViewContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Story Presenter which handle story screen events
 * Help to show list of stories , navigate to story summary and update story tasks
 *
 * @author Sara Elmoghazy
 */

public class StoryPresenter extends BasePresenter<StoriesViewContract> {

    @Inject
    GetStoriesRepository getStoriesRepository;

    public StoryPresenter(StoriesViewContract view) {
        super(view);
        GetStoriesComponent.Initializer.buildComponent().inject(this);
    }

    @Override
    public void start() {
        super.start();

        showLoadingIndicator();
        BaseFetchObserver<List<Story>> observer = new BaseFetchObserver<List<Story>>(this) {
            @Override
            public void onNext(List<Story> stories) {
                hideLoadingIndicator();
                getView().showStories(stories);
            }
        };
        getStoriesRepository.getStories().subscribe(observer);
    }

    /**
     * triggered when user click story summary
     *
     * @param story
     */
    public void onStorySummaryClicked(Story story) {
        getView().navigateToStorySummary(story);
    }

    /**
     * triggered when user click update tasks
     *
     * @param story
     */
    public void onUpdateTasksClicked(Story story) {
        getView().navigateToUpdateTasks(story);
    }
}
