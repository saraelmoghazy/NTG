package com.ntg.user.mvpsample.add_subtask;

import com.ntg.user.mvpsample.data.sourse.remote.RetrofitProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 2/8/2018.
 */
@Module
public class AddSubTaskModule {

    @Provides
    @Singleton
    AddSubTaskRepo provideAddSubTaskRepo(RetrofitProvider retrofitProvider){
        return AddSubTaskRepo.getInstance(retrofitProvider);
    }
}
