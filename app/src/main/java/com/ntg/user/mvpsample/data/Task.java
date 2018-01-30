package com.ntg.user.mvpsample.data;


import java.io.Serializable;

/**
 * @author Islam Eldsoke
 *POJO Class that contains info about mt task
 */

public class Task implements Serializable {
    private String id;
    private String title;
    private String description;
    private String completed;

    public Task() {
    }

    public Task(String id, String title, String description, String completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
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

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
