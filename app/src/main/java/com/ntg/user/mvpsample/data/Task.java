package com.ntg.user.mvpsample.data;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;





/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class Task {

    @SerializedName("TaskId")
    public String TaskId;
    @SerializedName("Title")
    public String Title;
    @SerializedName("Description")
    public String Desc;
    @SerializedName("Complete")
    public boolean Completed;
    @SerializedName("subtasks")
    private List<Subtask> subtasks;

    public Task(){

    }
    public Task(String title) {
        this(title, "", new ArrayList<Subtask>(0));
    }
    public Task(String title, String description) {
        this(title, description, new ArrayList<Subtask>(0));
    }

    public Task(String title,String descrpiotion,List<Subtask> subtasks){
        this.Title=title;
        this.Desc=descrpiotion;
        this.TaskId =UUID.randomUUID().toString();
        this.Completed=false;
        this.subtasks = subtasks;

    }
    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setCompleted(boolean completed) {
        Completed = completed;
    }

    public boolean isCompleted() {
        return Completed;
    }
    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }





}
