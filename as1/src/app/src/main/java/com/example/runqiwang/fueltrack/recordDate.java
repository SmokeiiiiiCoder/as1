package com.example.runqiwang.fueltrack;

import java.io.Serializable;

/**
 * Created by runqi on 31/01/2016.
 */
public class recordDate implements Serializable{
    private String recordDate;

    public recordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}