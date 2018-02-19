package com.ntg.user.mvpsample.add_subtask.model;

import com.ntg.user.mvpsample.network.remote.ForApplication;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */
@Module
@ForApplication
public class AddSubTaskModule {

    @Provides
    BaseAddSubTaskRepo provideRemoteAddSubTaskRepo(RetrofitProvider retrofitProvider) {
        return BaseAddSubTaskRepo.getInstance(retrofitProvider);
    }

    @Provides
    AddSubTaskRepository provideAddSubTaskRepo(BaseAddSubTaskRepo remoteAddSubTaskRepo) {
        return AddSubTaskRepository.getInstance(remoteAddSubTaskRepo);
    }
}
