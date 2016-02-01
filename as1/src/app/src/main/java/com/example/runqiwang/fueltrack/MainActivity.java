package com.example.runqiwang.fueltrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final String FILENAME = "logfile.bin";
    private ListView AllRecord;
    private TextView total;

    private static ArrayList<Product> products = new ArrayList<Product>();
    private ArrayAdapter<Product> adapter;

    public static ArrayList<Product> getEntry() {
        return products;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AllRecord = (ListView) findViewById(R.id.listView);
        total = (TextView) findViewById(R.id.total_cost);
        AllRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, edit.class);
                Product entry = adapter.getItem(position);
                intent.putExtra("logItemEdit", entry);
                intent.putExtra("logKey", position);
                startActivity(intent);

            }
        });


        Button ADDButton = (Button) findViewById(R.id.main_add);
        Button ClearButton = (Button) findViewById(R.id.main_clear);
        ADDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(MainActivity.this, add.class));
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });
        ClearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                products.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
                Cost();
            }
        });
    }
        @Override
        protected void onStart() {
            // TODO Auto-generated method stub
            super.onStart();
            saveInFile();
            adapter = new ArrayAdapter<Product>(this, R.layout.list_item, products);
            AllRecord.setAdapter(adapter);
            Cost();
        }

    public void Cost() {
        double total_cost = 0.0;
        for (int i = 0; i < products.size(); i++) {
            total_cost += products.get(i).getFcost();
        }
        // taken Jan-27-2016 from http://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        total.setText(String.format("Total cost: $ %.2f", total_cost));
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