package com.ntg.user.mvpsample.model;

import com.google.gson.annotations.SerializedName;

public class StoryTask {

    @SerializedName("progress")
    private int progress;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    public StoryTask(String title, int progress) {
        this.title = title;
        this.progress = progress;
    }


    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return
                "StoryTask{" +
                        ",progress = '" + progress + '\'' +
                        ",id = '" + id + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }
}