package com.ntg.user.mvpsample.stories.model;

import com.ntg.user.mvpsample.base.BaseObservable;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.model.StoryTask;
import com.ntg.user.mvpsample.network.remote.APIEndPoint;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import java.util.List;

import io.reactivex.Observable;

public class RemoteGetStoriesRepo extends BaseObservable implements GetStoriesDataSource {

    private static RemoteGetStoriesRepo instance;
    RetrofitProvider retrofitProvider;

    private RemoteGetStoriesRepo(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
    }

    public static RemoteGetStoriesRepo getInstance(RetrofitProvider retrofitProvider) {
        if (instance == null)
            instance = new RemoteGetStoriesRepo(retrofitProvider);

        return instance;
    }


    private Observable<List<Story>> convert(List<Story> stories) {
        for (Story story : stories) {
            if (story.getStoryTasks() != null && story.getStoryTasks().size() > 0) {
                getStoryProgress(story.getStoryTasks()).subscribe(progress -> story.setProgress(progress));
            }
        }

        return Observable.just(stories);
    }

    @Override
    public Observable<Integer> getStoryProgress(List<StoryTask> storyTasks) {
        return
                Observable.just(storyTasks)
                        .flatMapIterable(subTask -> subTask)
                        .map(subTask -> subTask.getProgress())
                        .toList()
                        .map(progress -> Utils.getAverage(progress)).toObservable();
    }

    @Override
    public Observable<List<Story>> getStories() {
        APIEndPoint apiService = retrofitProvider.getRetrofit().create(APIEndPoint.class);

        return getObservable(apiService.getStories().flatMap(this::convert));
    }
}
