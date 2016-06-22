package com.example.seongu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    View workTimeView, restTimeView;

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

        workTimeView = findViewById(R.id.workTimeView);
        restTimeView = findViewById(R.id.restTimeView);

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

    }

    class RequestThread extends Thread {
        public void run() {
            workTimeView.setVisibility(View.VISIBLE);
            restTimeView.setVisibility(View.INVISIBLE);
            while (true) {
                if (remainingSets == 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 1) {
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        workTimeView.setVisibility(View.VISIBLE);
                        restTimeView.setVisibility(View.INVISIBLE);
                    }

                    try{
                        Thread.sleep(1000);


                    }catch (InterruptedException e) {

                    }
                    break;
                } else if (remainingWorkMinTime == 0 && remainingWorkSecTime != 1) {
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        workTimeView.setVisibility(View.VISIBLE);
                        restTimeView.setVisibility(View.INVISIBLE);
                    }

                    try {
                        remainingWorkSecTime--;
                        Thread.sleep(1000);

                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {

                    }

                } else if ( remainingWorkMinTime != 0 && remainingWorkSecTime != 0 ) {
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        workTimeView.setVisibility(View.VISIBLE);
                        restTimeView.setVisibility(View.INVISIBLE);
                    }

                    try {
                        Thread.sleep(1000);
                        remainingWorkSecTime--;
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {

                    }
                }
                else if (remainingSets !=1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 1 && remainingRestMinTime == 0 && remainingRestSecTime == 1) {//check
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        workTimeView.setVisibility(View.VISIBLE);
                        restTimeView.setVisibility(View.INVISIBLE);
                    }
                    try {
                        Thread.sleep(1000);
                        remainingWorkSecTime--;
                        workTimeView.setVisibility(View.INVISIBLE);
                        restTimeView.setVisibility(View.VISIBLE);
                        Thread.sleep(1000);
                        remainingRestSecTime--;

                    } catch (InterruptedException e) {

                    }

                }
                else if (remainingWorkMinTime != 0 && remainingWorkSecTime == 0) {
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        workTimeView.setVisibility(View.VISIBLE);
                        restTimeView.setVisibility(View.INVISIBLE);
                    }

                    try {
                        Thread.sleep(1000);
                        remainingWorkMinTime--;
                        remainingWorkSecTime = 59;
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {

                    }
                }else if (remainingSets != 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 1 && remainingRestMinTime == 0 && remainingRestSecTime !=0) {//check
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        viewHandler.sendEmptyMessage(0);

                    }
                    try {
                        viewHandler.sendEmptyMessage(1);

                        Thread.sleep(1000);
                        remainingRestSecTime--;
                        handler.sendEmptyMessage(2);
                    } catch (InterruptedException e) {

                    }
                }
                else if (remainingSets != 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 1 && remainingRestMinTime != 0 && remainingRestSecTime !=0) {//check
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        workTimeView.setVisibility(View.VISIBLE);
                        restTimeView.setVisibility(View.INVISIBLE);
                    }
                    try {
                        Thread.sleep(1000);
                        remainingWorkSecTime--;

                        workTimeView.setVisibility(View.INVISIBLE);
                        restTimeView.setVisibility(View.VISIBLE);
                        Thread.sleep(1000);
                        remainingRestSecTime--;
                        handler.sendEmptyMessage(2);
                    } catch (InterruptedException e) {

                    }
                } else if (remainingSets != 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 0 && remainingRestMinTime != 0 && remainingRestSecTime == 0) {//check
                    if(workTimeView.getVisibility() == View.VISIBLE){
                        workTimeView.setVisibility(View.INVISIBLE);
                        restTimeView.setVisibility(View.VISIBLE);
                    }
                    try {
                        Thread.sleep(1000);
                        remainingRestMinTime--;
                        remainingRestSecTime = 59;
                        handler.sendEmptyMessage(3);

                    } catch (InterruptedException e) {

                    }
                }
                else if (remainingSets != 1 && remainingWorkMinTime == 0 &&  remainingWorkSecTime == 1 && remainingRestMinTime == 0 && remainingRestSecTime == 0) {//check

                    remainingWorkMinTime = initialWorkMinTime;
                    remainingWorkSecTime = initialWorkSecTime;
                    remainingRestMinTime = initialRestMinTime;
                    remainingRestSecTime = initialRestSecTime;
                    try {

                        remainingSets--;
                        Thread.sleep(1000);
                        handler.sendEmptyMessage(4);
                    }catch(InterruptedException e){

                    }
                }

                else if (remainingSets != 1 && remainingWorkMinTime == 0 &&  remainingWorkSecTime == 0 && remainingRestMinTime == 0 && remainingRestSecTime == 0) {//check

                    remainingWorkMinTime = initialWorkMinTime;
                    remainingWorkSecTime = initialWorkSecTime;
                    remainingRestMinTime = initialRestMinTime;
                    remainingRestSecTime = initialRestSecTime;

                    remainingSets--;
                    handler.sendEmptyMessage(4);

                }

            }

        }
        Handler viewHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    workTimeView.setVisibility(View.VISIBLE);
                    restTimeView.setVisibility(View.INVISIBLE);
                }else if(msg.what == 1){
                    workTimeView.setVisibility(View.INVISIBLE);
                    restTimeView.setVisibility(View.VISIBLE);
                }
            }
        };
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    txtWorkSec.setText(String.valueOf(remainingWorkSecTime));
                } else if (msg.what == 1) {
                    txtWorkMin.setText(String.valueOf(remainingWorkMinTime));
                    txtWorkSec.setText(String.valueOf(remainingWorkSecTime));
                } else if (msg.what == 2) {
                    txtRestSec.setText(String.valueOf(remainingRestSecTime));
                } else if (msg.what == 3) {
                    txtRestMin.setText(String.valueOf(remainingRestMinTime));
                    txtRestSec.setText(String.valueOf(remainingRestSecTime));
                } else if (msg.what == 4) {
                    txtSets.setText(String.valueOf(remainingSets));
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


