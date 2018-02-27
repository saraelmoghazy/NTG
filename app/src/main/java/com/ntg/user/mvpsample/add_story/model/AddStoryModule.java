package com.ntg.user.mvpsample.add_story.model;

import com.ntg.user.mvpsample.network.remote.ForApplication;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sara Elmoghazy
 */
@Module
@ForApplication
public class AddStoryModule {

    @Provides
    RemoteAddStoryRepo provideRemoteStoryRepo(RetrofitProvider retrofitProvider) {
        return RemoteAddStoryRepo.getInstance(retrofitProvider);
    }

    @Provides
    AddStoryRepository provideStoryRepo(RemoteAddStoryRepo remoteAddStoryRepo) {
        return AddStoryRepository.getInstance(remoteAddStoryRepo);
    }
}
