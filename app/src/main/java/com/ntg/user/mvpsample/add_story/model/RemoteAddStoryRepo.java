package com.ntg.user.mvpsample.add_story.model;

import com.ntg.user.mvpsample.base.BaseObservable;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.network.remote.APIEndPoint;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public class RemoteAddStoryRepo extends BaseObservable implements AddStoryDataSource {

    private static RemoteAddStoryRepo INSTANCE = null;
    RetrofitProvider retrofitProvider;

    private RemoteAddStoryRepo(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
    }

    public static RemoteAddStoryRepo getInstance(RetrofitProvider retrofitProvider) {
        if (INSTANCE == null)
            INSTANCE = new RemoteAddStoryRepo(retrofitProvider);
        return INSTANCE;
    }

    @Override
    public Observable<Story> saveStory(Story story) {
        APIEndPoint apiEndPoint = retrofitProvider.getRetrofit().create(APIEndPoint.class);
        return getObservable(apiEndPoint.saveStory(story));
    }
}
