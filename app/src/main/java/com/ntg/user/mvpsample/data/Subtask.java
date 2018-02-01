package com.ntg.user.mvpsample.data;

/**
 * Created by ilias on 31/01/2018.
 */

public class Subtask {

    private String title;
    private int progress;

    public Subtask(String title, int progress) {
        this.title = title;
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
