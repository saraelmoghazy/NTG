package com.ntg.user.mvpsample.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohamed yassin on 2/3/2018.
 */

public class Subtask {
    @SerializedName("subTitle")
    private String subtitle;
    @SerializedName(" progress")
    private int progress;

    public Subtask(String subtitle, int progress) {
        this.subtitle = subtitle;
        this.progress = progress;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
