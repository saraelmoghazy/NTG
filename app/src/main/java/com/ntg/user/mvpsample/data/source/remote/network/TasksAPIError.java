package com.ntg.user.mvpsample.data.source.remote.network;

/**
 * Created by ilias on 05/02/2018.
 */

public class TasksAPIError {

    private int code;
    private String message;

    public TasksAPIError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
