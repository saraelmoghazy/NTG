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

public class SubTask implements Parcelable {
    
    @SerializedName("subTitle")
    @Expose
    private String subTitle;
    
    @SerializedName("Progress")
    @Expose
    private String Progress;
    
    @SerializedName("subTasks")
    @Expose
    private List<Integer> subTaskProgress;
    
    public SubTask(String title, String Progress) {
        this.subTitle = title;
        this.Progress = Progress;
    }
    
    public String getSubTitle() {
        return subTitle;
    }
    
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    
    public String getProgress() {
        return Progress;
    }
    
    public void setProgress(String progress) {
        Progress = progress;
    }
    
    public List<Integer> getSubTaskProgress() {
        return subTaskProgress;
    }
    
    public void setSubTaskProgress(List<Integer> subTaskProgress) {
        this.subTaskProgress = subTaskProgress;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.subTitle);
        dest.writeString(this.Progress);
        dest.writeList(this.subTaskProgress);
    }
    
    protected SubTask(Parcel in) {
        this.subTitle = in.readString();
        this.Progress = in.readString();
        this.subTaskProgress = new ArrayList<Integer>();
        in.readList(this.subTaskProgress, Integer.class.getClassLoader());
    }
    
    public static final Creator<SubTask> CREATOR = new Creator<SubTask>() {
        @Override
        public SubTask createFromParcel(Parcel source) {
            return new SubTask(source);
        }
        
        @Override
        public SubTask[] newArray(int size) {
            return new SubTask[size];
        }
    };
}