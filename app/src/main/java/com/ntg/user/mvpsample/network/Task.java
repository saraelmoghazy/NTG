package com.ntg.user.mvpsample.network;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Islam Eldsoke
 *POJO Class that contains info about mt task
 */

public class Task implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("progress")
    private int progress;
    @SerializedName("subTasks")
    private List<SubTask> subTasks;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }



    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public Task(String title , String description){
        this.title=title;
        this.description=description;
    }
    public Task() {
    }

    public Task(String id, String title, String description , List<SubTask> subTasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.subTasks = subTasks;
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
}
