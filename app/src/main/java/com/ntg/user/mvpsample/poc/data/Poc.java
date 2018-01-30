package com.ntg.user.mvpsample.poc.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohamed yassin on 1/29/2018.
 */

public class Poc {
    @SerializedName("UserId")
    public String UserId;
    @SerializedName("Title")
    public String Title;

    @SerializedName("Complete")
    public boolean Completed;
    @SerializedName("id")
    private int id;

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

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean completed) {
        Completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
