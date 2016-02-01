package com.example.runqiwang.fueltrack;

import java.io.Serializable;

/**
 * Created by runqi on 31/01/2016.
 */
public class Fuel implements Serializable {
    private Float Funitcost;
    private Float Famount;

    private String Fgrade;

    public Fuel(String Fgrade, Float Famount, Float Funitcost) {
        this.Funitcost = Funitcost;
        this.Famount = Famount;
        this.Fgrade = Fgrade;
    }

    public Float getFunitcost() {
        return Funitcost;
    }

    public void setFunitcost(Float Funitcost) {
        this.Funitcost = Funitcost;
    }

    public Float getFamount() {
        return Famount;
    }

    public void setFamount(Float amount) {
        this.Famount = amount;
    }

    public Float getFcost() {
        Float Fcost = (Famount*Funitcost)/100;
        return Fcost;
    }



    public String getFgrade() {
        return Fgrade;
    }

    public void setFgrade(String Fgrade) {
        this.Fgrade = Fgrade;
    }
}
