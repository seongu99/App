package com.example.seongu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class playActivity extends AppCompatActivity {

    boolean finishFlag = true;
    ResponseHandler handler = new ResponseHandler();

    int remainingSets;
    int remainingWorkMinTime;
    int remainingWorkSecTime;
    int remainingRestMinTime;
    int remainingRestSecTime;

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

        remainingSets = data.sets;
        remainingWorkMinTime = data.workMinTime;
        remainingWorkSecTime = data.workSecTime;
        remainingRestMinTime = data.restMinTime;
        remainingRestSecTime = data.restSecTime;

        RequestThread thread = new RequestThread();
        thread.start();

    }

    class RequestThread extends Thread {
        public void run(){
            while(finishFlag){
                timeDecrease();
            }
        }
    }

    private void timeDecrease() {
        if(remainingSets == 0 && remainingWorkMinTime ==0 && remainingWorkSecTime ==0) {
            finishFlag = false;

        }
        else if(remainingSets != 0 && remainingWorkMinTime != 0 && remainingWorkSecTime !=0){
            try {
                handler.sendEmptyMessage(remainingWorkSecTime--);
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
        }
        else if(remainingSets != 0 && remainingWorkMinTime != 0 && remainingWorkSecTime ==0) {
            try{

                handler.sendEmptyMessage(remainingWorkMinTime--);
                handler.sendEmptyMessage(remainingWorkSecTime = 59);
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
        }
        else if (remainingSets != 0 && remainingWorkMinTime == 0 && remainingWorkSecTime ==0 && remainingWorkMinTime !=0 && remainingRestSecTime !=0) {
            try {
                handler.sendEmptyMessage(remainingRestSecTime--);
                Thread.sleep(1000);

            }catch (InterruptedException e) {

            }
        }
        else if(remainingSets != 0 && remainingWorkMinTime == 0 && remainingWorkSecTime ==0 && remainingWorkMinTime !=0 && remainingRestSecTime ==0) {
            try{
                handler.sendEmptyMessage(remainingRestMinTime--);
                handler.sendEmptyMessage(remainingRestSecTime = 59);
                Thread.sleep(1000);
            }catch(InterruptedException e) {

            }
        }
    }

    class ResponseHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

}
