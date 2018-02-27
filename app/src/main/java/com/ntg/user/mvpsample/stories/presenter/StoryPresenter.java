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
 * @author Sara Elmoghazy
 */

public class StoryPresenter extends BasePresenter<StoriesViewContract> {

    @Inject
    GetStoriesRepository getStoriesRepository;

    public StoryPresenter(StoriesViewContract view) {
        super(view);
        GetStoriesComponent.Initializer.buildComponent().inject(this);
    }


    private void loadStories() {
        showLoadingIndicator();
        BaseFetchObserver<List<Story>> observer = new BaseFetchObserver<List<Story>>(this) {
            @Override
            public void onNext(List<Story> tasks) {
                hideLoadingIndicator();
                getView().showStories(tasks);
            }
        };
        getStoriesRepository.getStories().subscribe(observer);
    }

    @Override
    public void start() {
        super.start();

        loadStories();
    }

    public void onStorySummaryClicked(Story story) {
        getView().navigateToStorySummaryFragment(story);
    }


    public void onUpdateTasksClicked(Story story) {
        getView().navigateToStorySummaryFragment(story);
    }
}
