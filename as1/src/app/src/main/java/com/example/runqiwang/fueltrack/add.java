package com.example.runqiwang.fueltrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by runqiwang on 16-01-28.
 */
public class add extends Activity implements Serializable{
    private static final String FILENAME = "logfile.bin";

    private EditText date;
    private EditText station;
    private EditText OReading;
    private EditText Fgrade;
    private EditText Famount;
    private EditText Funitcost;

    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        MainActivity.getEntry();

        Button ADD_ADD = (Button) findViewById(R.id.add_add);
        Button ADD_CANCEL = (Button) findViewById(R.id.add_cancel);

        date = (EditText) findViewById(R.id.Date_view2);
        station = (EditText) findViewById(R.id.Station_view2);
        OReading = (EditText) findViewById(R.id.OR_view2);
        Fgrade = (EditText) findViewById(R.id.Fgrade_view2);
        Famount = (EditText) findViewById(R.id.Famount_view2);
        Funitcost = (EditText) findViewById(R.id.Funitcost_view2);

        ADD_CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(add.this, MainActivity.class));
            }
        });

        ADD_ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(add.this,MainActivity.class));
                //change date to String format
                String date1 = date.getText().toString();
                //change station to string
                String station1 = station.getText().toString();
                //change OReading to int
                String OReading1 = OReading.getText().toString();
                Float OReading2 = Float.parseFloat(OReading1);
                //change Fgrade to string
                String Fgrade1 = Fgrade.getText().toString();
                //change Famount to int
                String Famount1 = Famount.getText().toString();
                Float Famount2 = Float.parseFloat(Famount1);
                //change Funitcost to int
                String Funitcost1 = Funitcost.getText().toString();
                Float Funitcost2 = Float.parseFloat(Funitcost1);

                recordDate date2 = new recordDate(date1);
                Station station2 = new Station(station1);
                OR OReading3 = new OR(OReading2);
                Fuel fuel = new Fuel(Fgrade1, Famount2, Funitcost2);

                Product record = new Product(date2, station2, OReading3, fuel);
                MainActivity.getEntry().add(record);
                adapter.notifyDataSetChanged();
                saveInFile();
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        saveInFile();
        adapter = new ArrayAdapter<Product>(this, R.layout.list_item, products);
    }

   //load the file
   public void loadFromFile() {
       try {
           FileInputStream fis = openFileInput(FILENAME);
           BufferedReader bin = new BufferedReader(new InputStreamReader(fis));

           Gson gson = new Gson();
           Type type = new TypeToken<ArrayList<Product>>() {}.getType();
           products = gson.fromJson(bin, type);
           fis.close();
       } catch (Exception e) { }
   }
    //save the file
    public void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(products, bout);
            bout.flush();
            fos.close();
            bout.close();
        } catch (Exception e) {       }
    }
}




