package com.ntg.user.mvpsample.local;

public class TaskModel {
    public String taskID,taskName, taskDesc;

    public TaskModel() {
    }

    public String getTaskID() {
        return taskID;

    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;

    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public TaskModel(String taskID, String taskName, String taskDesc) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
    }

    public TaskModel(String taskName, String taskDesc) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;

    }

}
