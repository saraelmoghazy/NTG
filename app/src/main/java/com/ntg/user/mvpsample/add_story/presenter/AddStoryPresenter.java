package com.ntg.user.mvpsample.add_story.presenter;

import com.ntg.user.mvpsample.add_story.model.AddStoryRepository;
import com.ntg.user.mvpsample.add_story.view.AddStoryViewContract;
import com.ntg.user.mvpsample.add_story.model.AddStoryComponent;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.model.Story;

import javax.inject.Inject;

/**
 * Story Presenter which handle add story screen events
 * Help to add story
 *
 * @author Sara Elmoghazy
 */
public class AddStoryPresenter extends BasePresenter<AddStoryViewContract> {

    @Inject
    AddStoryRepository repository;

    public AddStoryPresenter(AddStoryViewContract addStoryViewContract) {
        super(addStoryViewContract);
        AddStoryComponent.Initializer.buildComponent().inject(this);
    }

    /**
     * Save story
     *
     * @param story
     */
    private void saveStory(Story story) {
        showLoadingIndicator();
        BaseFetchObserver<Story> observer = new BaseFetchObserver<Story>(this) {
            @Override
            public void onNext(Story addedStory) {
                hideLoadingIndicator();
                getView().navigateToAddTasks(addedStory);
            }
        };
        repository.saveStory(story).subscribe(observer);
    }

    /**
     * triggered when screen click submit story
     *
     * @param title
     * @param description
     */
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
