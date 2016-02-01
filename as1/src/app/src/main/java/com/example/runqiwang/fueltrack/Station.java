package com.example.runqiwang.fueltrack;

import java.io.Serializable;

/**
 * Created by runqi on 31/01/2016.
 */
public class Station implements Serializable {
    private String station;

    public Station(String station) {
        this.station = station;
    }

    public String getstation() {
        return station;
    }

    public void setstation(String station) {
        this.station = station;
    }
}
