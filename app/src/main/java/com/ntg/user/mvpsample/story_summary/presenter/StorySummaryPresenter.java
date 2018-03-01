package com.ntg.user.mvpsample.story_summary.presenter;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.model.StoryTask;
import com.ntg.user.mvpsample.story_summary.view.StorySummaryViewContract;

/**
 * Story Summary presenter
 *
 * @author Sara Elmoghazy
 */
public class StorySummaryPresenter extends BasePresenter<StorySummaryViewContract> {

    private Story story;

    public StorySummaryPresenter(StorySummaryViewContract view, Story story) {
        super(view);
        this.story = story;
    }

    @Override
    public void start() {
        super.start();

        getView().showTitle(story.getTitle());
        getView().showDescription(story.getDescription());
        getView().showIcon(story.getTitle());
        getView().showStoryProgress(story.getProgress());
        showTasksProgress();
    }

    /**
     * calculate tasks summary (NEW , In progress , Done)
     */
    private void showTasksProgress() {
        int done = 0;
        int inProgress = 0;
        int notStarted = 0;
        if (story.getStoryTasks() != null && story.getStoryTasks().size() > 0) {
            for (StoryTask task : story.getStoryTasks()) {
                if (task.getProgress() == 0) notStarted++;
                else if (task.getProgress() > 0 && task.getProgress() < 100) inProgress++;
                else if (task.getProgress() == 100) done++;
            }
        }
        getView().showDoneTasks(done);
        getView().showNewTasks(notStarted);
        getView().showInProgressTasks(inProgress);
    }
}
