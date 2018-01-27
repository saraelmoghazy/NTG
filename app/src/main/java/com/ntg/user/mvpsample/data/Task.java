package com.ntg.user.mvpsample.data;

import java.util.UUID;

/**
 * Created by ilias on 25/01/2018.
 */

public class Task {
private String id;
private String title;
private String description;
private boolean isCompleted;

    public Task(String title) {
        this(title, "");
    }

    public Task(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.isCompleted = false;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
