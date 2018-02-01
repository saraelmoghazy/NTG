package com.ntg.user.mvpsample.remote;

/**
 * Created by GM7 on 1/29/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

public class Task {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("subTasks")
    @Expose
    private List<SubTask> subTasks;
    
    public List<SubTask> getSubTasks() {
        return subTasks;
    }
    
    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
    
    public Task(String title, String desc,List<SubTask> subTasks) {
        this.title = title;
        this.body = desc;
        this.id= UUID.randomUUID().toString();
        this.subTasks=subTasks;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}