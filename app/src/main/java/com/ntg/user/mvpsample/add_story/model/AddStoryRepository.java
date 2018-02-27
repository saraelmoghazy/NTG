package com.ntg.user.mvpsample.add_story.model;

import com.ntg.user.mvpsample.model.Story;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */
public class AddStoryRepository implements AddStoryDataSource {

    private static AddStoryRepository INSTANCE = null;
    private final AddStoryDataSource remoteAddStoryRepo;

    private AddStoryRepository(AddStoryDataSource remoteAddStoryRepo) {
        this.remoteAddStoryRepo = remoteAddStoryRepo;
    }

    public static AddStoryRepository getInstance(AddStoryDataSource remoteAddStoryRepo) {
        if (INSTANCE == null) {
            INSTANCE = new AddStoryRepository(remoteAddStoryRepo);
        }

        return INSTANCE;
    }

    @Override
    public Observable<Integer> saveStory(Story story) {
        return remoteAddStoryRepo.saveStory(story);
    }
}
