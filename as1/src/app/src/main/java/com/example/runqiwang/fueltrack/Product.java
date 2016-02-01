package com.example.runqiwang.fueltrack;

import java.io.Serializable;

/**
 * Created by runqiwang on 16-01-27.
 */
public class Product implements Serializable {
    private recordDate recorddate;
    private Station station;
    private OR OReading;
    private Fuel fuel;

    public void changeDate(String newDate){
        recorddate.setRecordDate(newDate);
    }

    public void changestation(String newStation){
        station.setstation(newStation);
    }

    public void changeOR(Float newOR){
        OReading.setOR(newOR);
    }

    public void changeFgrade(String Fgrade){
        fuel.setFgrade(Fgrade);
    }

    public void changeFamount(Float Faomunt){
        fuel.setFamount(Faomunt);
    }

    public void changeFunitcost(Float Fcost){
        fuel.setFunitcost(Fcost);
    }

    public String getRecordDate(){return recorddate.getRecordDate();}

    public String getstation(){return station.getstation();}

    public Float getOR(){return OReading.getOR();}

    public String getFgrade(){return fuel.getFgrade();}

    public Float getFamount(){return fuel.getFamount();}

    public Float getFunitcost(){return fuel.getFunitcost();}

    public Float getFcost(){return  fuel.getFcost();}

    public Product(recordDate recorddate, Station station, OR OReading,Fuel fuel){
        this.recorddate = recorddate;
        this.station = station;
        this.OReading = OReading;
        this.fuel = fuel;
    }

    @Override
    public String toString(){
        return "DATE:"+recorddate.getRecordDate()+"\n"+"STATION:"+station.getstation()+"\n"+"ODOMETER READING:"+OReading.getOR()+"\n"+ "FUEL GRADE:"
                +fuel.getFgrade()+"\n"+"FUEL AMOUNT:"+fuel.getFamount()+"\n"+"FUEL UNIT COST:"+fuel.getFunitcost()+"\n"+"FUEL COST:"+fuel.getFcost();
    }
}
