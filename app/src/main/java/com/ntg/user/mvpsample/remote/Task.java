package com.ntg.user.mvpsample.remote;
/**
 * Created by GM7 on 1/29/2018.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task implements Parcelable {
    
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
    
    public Task(String title, String body) {
        this(title, body, new ArrayList<>(0));
    }
    
    public Task(String title, String desc, List<SubTask> subTasks) {
        this.title = title;
        this.body = desc;
        this.id = UUID.randomUUID().toString();
        this.subTasks = subTasks;
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
    
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeString(this.id);
        dest.writeList(this.subTasks);
    }
    
    protected Task(Parcel in) {
        this.title = in.readString();
        this.body = in.readString();
        this.id = in.readString();
        this.subTasks = new ArrayList<SubTask>();
        in.readList(this.subTasks, SubTask.class.getClassLoader());
    }
    
    public static final Creator<Task> CREATOR = new Creator<Task>() {
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