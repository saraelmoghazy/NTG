package com.ntg.user.mvpsample.stories.view;

import android.view.View;

import com.ntg.user.mvpsample.model.Story;

/**
 * Created by Sara Elmoghazy on 27/02/2018.
 */

public class StorySummaryItem {

    private Story story;
    private View sharedElement;

    public StorySummaryItem(View sharedElement, Story story) {
        this.story = story;
        this.sharedElement = sharedElement;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public View getSharedElement() {
        return sharedElement;
    }

    public void setSharedElement(View sharedElement) {
        this.sharedElement = sharedElement;
    }
}
