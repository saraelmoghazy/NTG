package com.ntg.user.mvpsample.data;

import java.io.Serializable;

public class SubTask implements Serializable{

    private String id;
    private String title;
    private String description;
    private int progress;

    public SubTask() {
    }

    public SubTask(String id, String title, String description, int progress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.progress = progress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
