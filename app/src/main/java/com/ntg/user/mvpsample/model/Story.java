package com.ntg.user.mvpsample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Story implements Serializable {

    @SerializedName("progress")
    private int progress;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("subTasks")
    private List<StoryTask> storyTasks;

    @SerializedName("taskId")
    private int taskId;

    public Story(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setStoryTasks(List<StoryTask> storyTasks) {
        this.storyTasks = storyTasks;
    }

    public List<StoryTask> getStoryTasks() {
        return storyTasks;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getId() {
        return taskId;
    }

    @Override
    public String toString() {
        return
                "Story{" +
                        "description = '" + getDescription() + '\'' +
                        ",progress = '" + progress + '\'' +
                        ",title = '" + title + '\'' +
                        ",storyTasks = '" + storyTasks + '\'' +
                        ",taskId = '" + taskId + '\'' +
                        "}";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}