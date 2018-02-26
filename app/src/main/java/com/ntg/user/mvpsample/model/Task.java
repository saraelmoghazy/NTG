package com.ntg.user.mvpsample.model;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("description")
    private String description;

    @SerializedName("progress")
    private int progress;

    @SerializedName("title")
    private String title;

    @SerializedName("SubTasks")
    private List<SubTask> subTasks;

    @SerializedName("taskId")
    private int taskId;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return
                "Task{" +
                        "description = '" + description + '\'' +
                        ",progress = '" + progress + '\'' +
                        ",title = '" + title + '\'' +
                        ",subTasks = '" + subTasks + '\'' +
                        ",taskId = '" + taskId + '\'' +
                        "}";
    }
}