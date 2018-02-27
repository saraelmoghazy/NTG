package com.ntg.user.mvpsample.stories.model;

import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.model.StoryTask;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public interface GetStoriesDataSource {

    Observable<Integer> getStoryProgress(List<StoryTask> storyTasks);

    Observable<List<Story>> getStories();
}
