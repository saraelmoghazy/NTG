package com.ntg.user.mvpsample.stories.model;

import com.ntg.user.mvpsample.model.StoryTask;
import com.ntg.user.mvpsample.model.Story;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public class GetStoriesRepository implements GetStoriesDataSource {

    private static GetStoriesRepository INSTANCE = null;
    private final GetStoriesDataSource remoteGetStoriesDataSource;

    private GetStoriesRepository(GetStoriesDataSource remoteGetStoriesDataSource) {
        this.remoteGetStoriesDataSource = remoteGetStoriesDataSource;
    }

    public static GetStoriesRepository getInstance(GetStoriesDataSource remoteGetStoriesDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GetStoriesRepository(remoteGetStoriesDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<Integer> getStoryProgress(List<StoryTask> storyTasks) {
        return remoteGetStoriesDataSource.getStoryProgress(storyTasks);
    }

    @Override
    public Observable<List<Story>> getStories() {
        return remoteGetStoriesDataSource.getStories();
    }
}
