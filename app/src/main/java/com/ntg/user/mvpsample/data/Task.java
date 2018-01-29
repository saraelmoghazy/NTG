package com.ntg.user.mvpsample.data;

import java.util.UUID;

/**
 * Created by devsaad on 1/28/2018.
 */

public class Task {
    private String id;
    private String tittle;
    private String describtion;

    public String getTittle() {
        return tittle;
    }

    public String getDescribtion() {
        return describtion;
    }



    public void setId(String id) {
        this.id = UUID.randomUUID().toString();
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}