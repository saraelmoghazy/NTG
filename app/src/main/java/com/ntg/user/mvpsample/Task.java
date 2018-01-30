package com.ntg.user.mvpsample;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;


/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class Task {
    @SerializedName("UserId")
    public String UserId;
    @SerializedName("Title")
    public String Title;
    @SerializedName("Description")
    public String Desc;
    @SerializedName("Complete")
    public boolean Completed;

    public Task(String title, String descrpiotion) {
        this.Title = title;
        this.Desc = descrpiotion;
        this.UserId = UUID.randomUUID().toString();
        this.Completed = false;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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


}
