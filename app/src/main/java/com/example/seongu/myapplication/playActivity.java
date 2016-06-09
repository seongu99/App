package com.example.seongu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class playActivity extends AppCompatActivity {

    EditText txtSets = (EditText) findViewById(R.id.sets);
    EditText txtWorkMin = (EditText) findViewById(R.id.minWorkTime);
    EditText txtWorkSec = (EditText) findViewById(R.id.secWorkTime);
    EditText txtRestMin = (EditText) findViewById(R.id.minRestTime);
    EditText txtRestSec = (EditText) findViewById(R.id.secRestTime);

    boolean finishFlag = true;
    workTimeHandler1 workTimeHandler1 = new workTimeHandler1();
    workTimeHandler2 workTimeHandler2 = new workTimeHandler2();
    restTimeHandler1 restTimeHandler1 =new restTimeHandler1();
    restTimeHandler1 restTimeHandler2 =new restTimeHandler1();
    setTimeHandler1 setTimeHandler1 = new setTimeHandler1();

    //Message setTimeMessage;
    Message workTimeMessage1;
    Message workTimeMessage2;
    //Message restTimeMessage1;
    //Message restTimeMessage2;

    int remainingSets;
    int remainingWorkMinTime;
    int remainingWorkSecTime;
    int remainingRestMinTime;
    int remainingRestSecTime;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub);

        Intent intent = getIntent();
        data = (Data)intent.getSerializableExtra("data");

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
        else if(remainingWorkMinTime != 0 && remainingWorkSecTime !=0){
            try {
                Thread.sleep(1000);

                workTimeMessage1 = workTimeHandler1.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("workSecData", remainingWorkSecTime--);
                workTimeMessage1.setData(bundle);

                workTimeHandler1.sendMessage(workTimeMessage1);
            }catch (InterruptedException e){

            }
        }
        else if(remainingWorkMinTime != 0 && remainingWorkSecTime ==0) {
            try{
                Thread.sleep(1000);
                workTimeMessage1 = workTimeHandler2.obtainMessage();
                workTimeMessage2 = workTimeHandler2.obtainMessage();

                Bundle bundle = new Bundle();
                bundle.putInt("workMinData",remainingWorkMinTime--);
                workTimeMessage1.setData(bundle);
                workTimeHandler2.sendMessage(workTimeMessage1);

                Bundle bundle2 = new Bundle();
                bundle2.putInt("workSecData", remainingWorkSecTime = 59);
                workTimeMessage2.setData(bundle2);
                workTimeHandler2.sendMessage(workTimeMessage2);

            }catch (InterruptedException e){

            }
        }
        else if (remainingSets != 0 && remainingWorkMinTime == 0 && remainingWorkSecTime ==0 && remainingRestMinTime !=0 && remainingRestSecTime !=0) {
            try {
                Thread.sleep(1000);
                workTimeMessage1 = restTimeHandler1.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("restSecData", remainingRestSecTime--);
                workTimeMessage1.setData(bundle);

                restTimeHandler1.sendMessage(workTimeMessage1);

            }catch (InterruptedException e) {

            }
        }
        else if(remainingSets != 0 && remainingWorkMinTime == 0 && remainingWorkSecTime ==0 && remainingRestMinTime !=0 && remainingRestSecTime ==0) {
            try{
                Thread.sleep(1000);
                workTimeMessage1 = restTimeHandler1.obtainMessage();
                workTimeMessage2 = restTimeHandler2.obtainMessage();

                Bundle bundle = new Bundle();
                bundle.putInt("restMinData",remainingRestMinTime--);
                workTimeMessage1.setData(bundle);
                workTimeHandler2.sendMessage(workTimeMessage1);

                Bundle bundle2 = new Bundle();
                bundle2.putInt("restSecData", remainingRestSecTime = 59);
                workTimeMessage2.setData(bundle2);
                workTimeHandler2.sendMessage(workTimeMessage2);

            }catch(InterruptedException e) {

            }
        }
        else if(remainingSets != 0 && remainingWorkMinTime == 0 && remainingWorkSecTime ==0 && remainingRestMinTime ==0 && remainingRestSecTime ==0) {

            remainingWorkMinTime = data.workMinTime;
            remainingWorkSecTime = data.workSecTime;
            remainingRestMinTime = data.restMinTime;
            remainingRestSecTime = data.restSecTime;

            try {
                Thread.sleep(1000);
                workTimeMessage1 = setTimeHandler1.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("setsData", remainingSets--);
                workTimeMessage1.setData(bundle);

                setTimeHandler1.sendMessage(workTimeMessage1);

                timeDecrease();
           }catch (InterruptedException e){

           }
        }

    }

    class workTimeHandler1 extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("workSecData");

            txtWorkSec.setText(data);
        }
    }

    class workTimeHandler2 extends  Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("workMinData");
            txtWorkMin.setText(data);

            Bundle bundle2 = msg.getData();
            int data2 = bundle.getInt("workSecData");
            txtWorkSec.setText(data2);

        }
    }

    class restTimeHandler1 extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("workSecData");

            txtWorkSec.setText(data);
        }
    }

    class restTimeHandler2 extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("workMinData");
            txtWorkMin.setText(data);

            Bundle bundle2 = msg.getData();
            int data2 = bundle.getInt("workSecData");
            txtWorkSec.setText(data2);

        }
    }

    class setTimeHandler1 extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int data = bundle.getInt("setsData");

            txtSets.setText(data);
        }
    }
}
