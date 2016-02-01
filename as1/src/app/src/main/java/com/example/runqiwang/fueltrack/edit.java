package com.example.runqiwang.fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by runqi on 31/01/2016.
 */
public class edit extends Activity implements Serializable {
    private EditText date;
    private EditText station;
    private EditText OR;
    private EditText Fgrade;
    private EditText Famount;
    private EditText Funitcost;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        Intent intent = getIntent();
        final Product entry = MainActivity.getEntry().get((Integer) intent.getSerializableExtra("logKey"));

        date = (EditText) findViewById(R.id.Date_Edit2);
        station = (EditText) findViewById(R.id.Station_Edit2);
        OR = (EditText) findViewById(R.id.OR_Edit2);
        Fgrade = (EditText) findViewById(R.id.Fgrade_Edit2);
        Famount = (EditText) findViewById(R.id.Famount_Edit2);
        Funitcost = (EditText) findViewById(R.id.Funitcost_Edit2);

        date.setText(entry.getRecordDate(), TextView.BufferType.EDITABLE);
        station.setText(entry.getstation(), TextView.BufferType.EDITABLE);
        OR.setText(entry.getOR().toString(), TextView.BufferType.EDITABLE);
        Fgrade.setText(entry.getFgrade(), TextView.BufferType.EDITABLE);
        Famount.setText(entry.getFamount().toString(), TextView.BufferType.EDITABLE);
        Funitcost.setText(entry.getFunitcost().toString(), TextView.BufferType.EDITABLE);

        Button EDIT_EDIT = (Button) findViewById(R.id.edit_edit);
        Button EDIT_CANCEL = (Button) findViewById(R.id.edit_cancel);

        EDIT_CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                startActivity(new Intent(edit.this, MainActivity.class));
            }
        });

        EDIT_EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                String Date = date.getText().toString();
                String Station = station.getText().toString();
                Float OReading = Float.valueOf(OR.getText().toString());
                String fgrade = Fgrade.getText().toString();
                Float famount= Float.valueOf(Famount.getText().toString());
                Float funitcost = Float.valueOf(Funitcost.getText().toString());

                entry.changeDate(Date);
                entry.changestation(Station);
                entry.changeOR(OReading);
                entry.changeFgrade(fgrade);
                entry.changeFamount(famount);
                entry.changeFunitcost(funitcost);

                startActivity(new Intent(edit.this, MainActivity.class));
            }
        });
    }
}
