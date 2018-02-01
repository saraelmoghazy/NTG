package com.ntg.user.mvpsample.remote;

/**
 * Created by GM7 on 1/29/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class SubTask {

    @SerializedName("subTitle")
    @Expose
    private String subTitle;
    
    @SerializedName("Progress")
    @Expose
    private String Progress;
    
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
}