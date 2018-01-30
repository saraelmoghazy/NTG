package com.ntg.user.mvpsample.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.core.deps.guava.base.Strings;


import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;





/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class Task {

 /* @NonNull
    private final String mId;

    @Nullable
    private final String mTitle;

    @Nullable
    private final String mDescription;

    private final boolean mCompleted;*/



    @SerializedName("UserId")
    public String UserId;
    @SerializedName("Title")
    public String Title;
    @SerializedName("Description")
    public String Desc;
    @SerializedName("Complete")
    public boolean Completed;

    public Task(String title,String descrpiotion){
        this.Title=title;
        this.Desc=descrpiotion;
        this.UserId=UUID.randomUUID().toString();
        this.Completed=false;

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

   /*  public Task(@Nullable String title, @Nullable String description) {
        this(title, description, UUID.randomUUID().toString(), false);
    }*/


   /*public Task(@Nullable String title, @Nullable String description, @NonNull String id) {
        this(title, description, id, false);
    }


    public Task(@Nullable String title, @Nullable String description, boolean completed) {
        this(title, description, UUID.randomUUID().toString(), completed);
    }



    public Task(@Nullable String title, @Nullable String description,
                @NonNull String id, boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }*/

  /*  @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }*/

    @Nullable
    public String getTitleForList() {
        if (!Strings.isNullOrEmpty(Title)) {
            return Title;
        } else {
            return Desc;
        }
    }

    @Nullable
    public String getDescription() {
        return Desc;
    }

    public boolean isCompleted() {
        return Completed;
    }

    public boolean isActive() {
        return !Completed;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(Title) &&
                Strings.isNullOrEmpty(Desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equal(UserId, task.UserId) &&
                Objects.equal(Title, task.Title) &&
                Objects.equal(Desc, task.Desc);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(UserId, Title, Desc);
    }

    @Override
    public String toString() {
        return "Task with title " + Title;
    }

}
