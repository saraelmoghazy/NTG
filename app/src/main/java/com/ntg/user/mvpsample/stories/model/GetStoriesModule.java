package com.ntg.user.mvpsample.stories.model;

import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sara Elmoghazy
 */
@Module
public class GetStoriesModule {

    @Provides
    RemoteGetStoriesRepo provideRemoteStoryRepo(RetrofitProvider retrofitProvider) {
        return RemoteGetStoriesRepo.getInstance(retrofitProvider);
    }

    @Provides
    @Singleton
    GetStoriesRepository provideStoryRepo(RemoteGetStoriesRepo remoteGetStoriesRepo) {
        return GetStoriesRepository.getInstance(remoteGetStoriesRepo);
    }
}
