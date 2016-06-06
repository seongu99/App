package com.example.seongu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class playActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub);

        Intent intent = getIntent();
        Data data = (Data)intent.getSerializableExtra("data");

        EditText txtSets = (EditText) findViewById(R.id.sets);
        EditText txtWorkMin = (EditText) findViewById(R.id.minWorkTime);
        EditText txtWorkSec = (EditText) findViewById(R.id.secWorkTime);
        EditText txtRestMin = (EditText) findViewById(R.id.minRestTime);
        EditText txtRestSec = (EditText) findViewById(R.id.secRestTime);

        txtSets.setText(String.valueOf(data.sets));
        txtWorkMin.setText(String.valueOf(data.workMinTime));
        txtWorkSec.setText(String.valueOf(data.workSecTime));
        txtRestMin.setText(String.valueOf(data.restMinTime));
        txtRestSec.setText(String.valueOf(data.restSecTime));

    }
}
