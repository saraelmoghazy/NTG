package com.ntg.user.mvpsample.add_story.model;

import com.ntg.user.mvpsample.model.Story;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public interface AddStoryDataSource {

    Observable<Story> saveStory(Story story);
}
