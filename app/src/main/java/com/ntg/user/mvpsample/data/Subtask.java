package com.ntg.user.mvpsample.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ilias on 31/01/2018.
 */

public class Subtask implements Parcelable {

    private String title;
    private int progress;

    public Subtask(String title, int progress) {
        this.title = title;
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.progress);
    }

    protected Subtask(Parcel in) {
        this.title = in.readString();
        this.progress = in.readInt();
    }

    public static final Parcelable.Creator<Subtask> CREATOR = new Parcelable.Creator<Subtask>() {
        @Override
        public Subtask createFromParcel(Parcel source) {
            return new Subtask(source);
        }

        @Override
        public Subtask[] newArray(int size) {
            return new Subtask[size];
        }
    };
}
