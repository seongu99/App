package com.example.seongu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {


    int remainingSets = 0;
    int remainingWorkMinTime = 0;
    int remainingWorkSecTime = 0;
    int remainingRestMinTime = 0;
    int remainingRestSecTime = 0;


    int initialWorkMinTime = 0;
    int initialWorkSecTime = 0;
    int initialRestMinTime = 0;
    int initialRestSecTime = 0;

    TextView txtSets;
    TextView txtWorkMin;
    TextView txtWorkSec;
    TextView txtRestMin;
    TextView txtRestSec;

   // Intent intent = getIntent();
    //Data data = (Data) intent.getSerializableExtra("data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub);

       Intent intent = getIntent();
       Data data = (Data) intent.getSerializableExtra("data");

        txtSets = (TextView) findViewById(R.id.sets);
        txtWorkMin = (TextView) findViewById(R.id.minWorkTime);
        txtWorkSec = (TextView) findViewById(R.id.secWorkTime);
        txtRestMin = (TextView) findViewById(R.id.minRestTime);
        txtRestSec = (TextView) findViewById(R.id.secRestTime);

        txtSets.setText(String.valueOf(data.sets));
        txtWorkMin.setText(String.valueOf(data.workMinTime));
        txtWorkSec.setText(String.valueOf(data.workSecTime));
        txtRestMin.setText(String.valueOf(data.restMinTime));
        txtRestSec.setText(String.valueOf(data.restSecTime));

        remainingSets = data.sets;
        remainingWorkMinTime = data.workMinTime;
        remainingWorkSecTime = data.workSecTime;
        remainingRestMinTime = data.restMinTime;
        remainingRestSecTime = data.restSecTime;

        initialWorkMinTime = data.workMinTime;
        initialWorkSecTime = data.workSecTime;
        initialRestMinTime = data.restMinTime;
        initialRestSecTime = data.restSecTime;

        RequestThread thread = new RequestThread();
        thread.start();
        Toast.makeText(this, "workout is finished", Toast.LENGTH_LONG).show();
    }

    class RequestThread extends Thread {
        public void run() {
            while (true) {
                if (remainingSets == 0 && remainingWorkMinTime == 0 && remainingWorkSecTime == 0) {
                    break;
                } else if (remainingWorkMinTime != 0 && remainingWorkSecTime != 0) {
                    remainingWorkSecTime--;
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                } else if (remainingWorkMinTime != 0 && remainingWorkSecTime == 0) {
                    remainingWorkMinTime--;
                    remainingWorkSecTime = 59;
                    handler.sendEmptyMessage(1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                } else if (remainingSets != 0 && remainingWorkMinTime == 0 && remainingWorkSecTime == 0 && remainingRestMinTime != 0 && remainingRestSecTime != 0) {
                    remainingRestSecTime--;
                    handler.sendEmptyMessage(2);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                } else if (remainingSets != 0 && remainingWorkMinTime == 0 && remainingWorkSecTime == 0 && remainingRestMinTime != 0 && remainingRestSecTime == 0) {
                    remainingRestMinTime--;
                    remainingRestSecTime = 59;
                    handler.sendEmptyMessage(3);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                } else if (remainingSets != 0 && remainingWorkMinTime == 0 && remainingWorkSecTime == 0 && remainingRestMinTime == 0 && remainingRestSecTime == 0) {

                    remainingWorkMinTime = initialWorkMinTime;
                    remainingWorkSecTime = initialWorkSecTime;
                    remainingRestMinTime = initialRestMinTime;
                    remainingRestSecTime = initialRestSecTime;

                    remainingSets--;
                    handler.sendEmptyMessage(4);
                    try {
                        Thread.sleep(1000);


                    } catch (InterruptedException e) {

                    }
                }
            }
        }

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    txtWorkSec.setText(String.valueOf(remainingWorkSecTime));
                } else if (msg.what == 1) {
                    txtWorkMin.setText(String.valueOf(remainingWorkMinTime--));
                    txtWorkSec.setText(String.valueOf(remainingWorkSecTime = 59));
                } else if (msg.what == 2) {
                    txtRestSec.setText(String.valueOf(remainingRestSecTime--));

                } else if (msg.what == 3) {
                    txtRestMin.setText(String.valueOf(remainingRestMinTime--));
                    txtRestSec.setText(String.valueOf(remainingRestSecTime = 59));
                } else if (msg.what == 4) {
                    txtSets.setText(String.valueOf(remainingSets--));
                    txtWorkMin.setText(String.valueOf(remainingWorkMinTime));
                    txtWorkSec.setText(String.valueOf(remainingWorkSecTime));
                    txtRestMin.setText(String.valueOf(remainingRestMinTime));
                    txtRestSec.setText(String.valueOf(remainingRestSecTime));
                } else {
                    Toast.makeText(playActivity.this, "Unexpected error occured", Toast.LENGTH_LONG).show();
                }
            }
        };


    }
}


