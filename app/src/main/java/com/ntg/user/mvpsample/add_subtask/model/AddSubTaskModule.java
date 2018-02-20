package com.ntg.user.mvpsample.add_subtask.model;

import com.ntg.user.mvpsample.network.remote.ForApplication;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sara Elmoghazy
 */
@Module
@ForApplication
public class AddSubTaskModule {

    @Provides
    RemoteAddSubTaskRepo provideRemoteAddSubTaskRepo(RetrofitProvider retrofitProvider) {
        return RemoteAddSubTaskRepo.getInstance(retrofitProvider);
    }

    @Provides
    AddSubTaskRepository provideAddSubTaskRepo(RemoteAddSubTaskRepo remoteAddSubTaskRepo) {
        return AddSubTaskRepository.getInstance(remoteAddSubTaskRepo);
    }
}
