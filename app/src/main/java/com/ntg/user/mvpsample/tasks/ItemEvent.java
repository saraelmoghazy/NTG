package com.ntg.user.mvpsample.tasks;

import android.support.annotation.StringDef;

import com.ntg.user.mvpsample.data.Task;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ilias on 11/02/2018.
 */

public class ItemEvent {

    private Task task;
    @EventType private String eventType;

    ItemEvent(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    String getEventType() {
        return eventType;
    }

    void setEventType(@EventType String eventType) {
        this.eventType = eventType;
    }

    @StringDef({EventType.CLICK, EventType.LONG_CLICK})

    @Retention(RetentionPolicy.RUNTIME)
    @interface EventType {
        String CLICK = "click";
        String LONG_CLICK = "long_click";
    }
}

