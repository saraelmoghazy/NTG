package com.ntg.user.mvpsample.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ilias on 25/01/2018.
 */

public class Task implements Parcelable {

    @SerializedName("taskId")
    private String id;
    private String title;
    private String description;
    private boolean isCompleted;
    private List<Subtask> subtasks;

    public Task(String title) {
        this(title, "", new ArrayList<>(0));
    }

    public Task(String title, String description) {
        this(title, description, new ArrayList<>(0));
    }

    public Task(String title, String description, List<Subtask> subtasks) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.isCompleted = false;
        this.subtasks = subtasks;
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

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeByte(this.isCompleted ? (byte) 1 : (byte) 0);
        dest.writeList(this.subtasks);
    }

    protected Task(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.isCompleted = in.readByte() != 0;
        this.subtasks = new ArrayList<Subtask>();
        in.readList(this.subtasks, Subtask.class.getClassLoader());
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
