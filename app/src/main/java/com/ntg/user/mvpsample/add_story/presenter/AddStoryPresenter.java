package com.ntg.user.mvpsample.add_story.presenter;

import com.ntg.user.mvpsample.add_story.model.AddStoryRepository;
import com.ntg.user.mvpsample.add_story.view.AddStoryViewContract;
import com.ntg.user.mvpsample.add_story.model.AddStoryComponent;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.model.Story;

import javax.inject.Inject;

/**
 * @author Sara Elmoghazy
 */

public class AddStoryPresenter extends BasePresenter<AddStoryViewContract> {

    @Inject
    AddStoryRepository repository;

    public AddStoryPresenter(AddStoryViewContract addStoryViewContract) {
        super(addStoryViewContract);
        AddStoryComponent.Initializer.buildComponent().inject(this);
    }

    private void saveStory(Story story) {
        showLoadingIndicator();
        BaseFetchObserver<Integer> observer = new BaseFetchObserver<Integer>(this) {
            @Override
            public void onNext(Integer storyId) {
                hideLoadingIndicator();
                getView().navigateToAddTasksFragments(storyId);
            }
        };
        repository.saveStory(story).subscribe(observer);
    }

    public void onSubmitStory(String title, String description) {
        if (title.isEmpty()) {
            getView().showTitleMissingError();
        } else if (description.isEmpty()) {
            getView().showDescriptionMissingError();
        } else {
            saveStory(new Story(title, description));
        }
    }
}
