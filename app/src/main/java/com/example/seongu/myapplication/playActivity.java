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

    private static final int MSG_WORK_SEC_CHANGED = 0;
    private static final int MSG_WORK_MINSEC_CHANGED = 1;
    private static final int MSG_REST_SEC_CHANGED = 2;
    private static final int MSG_REST_MINSEC_CHANGED = 3;
    private static final int MSG_SET_CHANGED = 4;
    private static final int MSG_SEE_WORKVIEW = 5;
    private static final int MSG_SEE_RESTVIEW = 6;
    private static final int MSG_SETVIEW_CHANGED = 7;
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

        TimerThread thread = new TimerThread();
        thread.start();

    }

    class TimerThread extends Thread {
        public void run() {
            handler.sendEmptyMessage(MSG_SEE_WORKVIEW);
            while (true) {
                if (remainingSets == 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 1) {
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_WORKVIEW);
                    }

                    try{
                        Thread.sleep(1000);


                    }catch (InterruptedException e) {

                    }
                    break;
                } else if (remainingWorkMinTime == 0 && remainingWorkSecTime >=2) {
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_WORKVIEW);
                    }

                    try {
                        Thread.sleep(1000);
                        remainingWorkSecTime--;


                        handler.sendEmptyMessage(MSG_WORK_SEC_CHANGED);
                    } catch (InterruptedException e) {

                    }


                }else if (remainingSets > 1 && remainingWorkMinTime == 0 &&  remainingWorkSecTime == 1 && remainingRestMinTime == 0 && remainingRestSecTime == 0) {//check

                    remainingWorkMinTime = initialWorkMinTime;
                    remainingWorkSecTime = initialWorkSecTime;
                    remainingRestMinTime = initialRestMinTime;
                    remainingRestSecTime = initialRestSecTime;
                    try {

                        Thread.sleep(1000);
                        remainingSets--;

                        handler.sendEmptyMessage(MSG_SET_CHANGED);
                    }catch(InterruptedException e){

                    }
                }

                else if ( remainingWorkMinTime != 0 && remainingWorkSecTime != 0 ) {
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_WORKVIEW);
                    }

                    try {
                        Thread.sleep(1000);
                        remainingWorkSecTime--;
                        handler.sendEmptyMessage(MSG_WORK_SEC_CHANGED);
                    } catch (InterruptedException e) {

                    }
                }
                else if (remainingSets !=1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 1 && remainingRestMinTime == 0 && remainingRestSecTime == 1) {//check
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        try {
                            Thread.sleep(1000);
                            remainingWorkMinTime = initialWorkMinTime;
                            remainingWorkSecTime = initialWorkSecTime;
                            remainingRestMinTime = initialRestMinTime;
                            remainingRestSecTime = initialRestSecTime;
                            remainingSets--;

                            handler.sendEmptyMessage(MSG_SET_CHANGED);
                            handler.sendEmptyMessage(MSG_SEE_WORKVIEW);

                        } catch (InterruptedException e) {
                        }

                    }

                    try {
                        Thread.sleep(1000);
                        remainingWorkSecTime--;

                        handler.sendEmptyMessage(MSG_SEE_RESTVIEW);
                        Thread.sleep(1000);
                        remainingRestSecTime--;

                    } catch (InterruptedException e) {

                    }

                }
                else if (remainingWorkMinTime != 0 && remainingWorkSecTime == 0) {
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_WORKVIEW);
                    }

                    try {
                        Thread.sleep(1000);
                        remainingWorkMinTime--;
                        remainingWorkSecTime = 59;
                        handler.sendEmptyMessage(MSG_WORK_MINSEC_CHANGED);
                    } catch (InterruptedException e) {

                    }
                }else if (remainingSets != 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 1 && remainingRestMinTime == 0 && remainingRestSecTime !=0) {//check
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_WORKVIEW);

                    }
                    try {
                        Thread.sleep(1000);
                        remainingWorkSecTime--;
                        handler.sendEmptyMessage(MSG_SEE_RESTVIEW);

                        Thread.sleep(1000);
                        remainingRestSecTime--;
                        handler.sendEmptyMessage(MSG_REST_SEC_CHANGED);
                    } catch (InterruptedException e) {

                    }
                }
                else if (remainingSets != 1 && remainingWorkMinTime == 0 &&  remainingWorkSecTime == 0 && remainingRestMinTime == 0 && remainingRestSecTime == 0) {//check

                    remainingWorkMinTime = initialWorkMinTime;
                    remainingWorkSecTime = initialWorkSecTime;
                    remainingRestMinTime = initialRestMinTime;
                    remainingRestSecTime = initialRestSecTime;

                    remainingSets--;

                    handler.sendEmptyMessage(MSG_SETVIEW_CHANGED);

                }
                else if (remainingSets != 1 && remainingWorkMinTime == 0 && remainingWorkSecTime >= 0 && remainingRestMinTime == 0 && remainingRestSecTime !=0) {//check
                    if(workTimeView.getVisibility() == View.VISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_RESTVIEW);
                    }
                    try {

                        Thread.sleep(1000);
                        remainingRestSecTime--;
                        handler.sendEmptyMessage(MSG_REST_SEC_CHANGED);
                    } catch (InterruptedException e) {

                    }
                }
                else if (remainingSets != 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 0 && remainingRestMinTime != 0 && remainingRestSecTime !=0) {//check
                    if(workTimeView.getVisibility() == View.VISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_RESTVIEW);
                    }
                    try {

                        Thread.sleep(1000);
                        remainingRestSecTime--;
                        handler.sendEmptyMessage(MSG_REST_SEC_CHANGED);
                    } catch (InterruptedException e) {

                    }
                }  else if (remainingSets != 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 1 && remainingRestMinTime != 0 && remainingRestSecTime !=0) {//check
                    if(workTimeView.getVisibility() == View.INVISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_WORKVIEW);
                    }
                    try {
                        Thread.sleep(1000);
                        remainingWorkSecTime--;

                        handler.sendEmptyMessage(MSG_SEE_RESTVIEW);
                        Thread.sleep(1000);
                        remainingRestSecTime--;
                        handler.sendEmptyMessage(MSG_REST_SEC_CHANGED);
                    } catch (InterruptedException e) {

                    }
                }else if (remainingSets != 1 && remainingWorkMinTime == 0 && remainingWorkSecTime == 0 && remainingRestMinTime != 0 && remainingRestSecTime == 0) {//check
                    if(workTimeView.getVisibility() == View.VISIBLE){
                        handler.sendEmptyMessage(MSG_SEE_RESTVIEW);
                    }
                    try {
                        Thread.sleep(1000);
                        remainingRestMinTime--;
                        remainingRestSecTime = 59;
                        handler.sendEmptyMessage(MSG_REST_MINSEC_CHANGED);

                    } catch (InterruptedException e) {

                    }
                }

                else if (remainingSets != 1 && remainingWorkMinTime == 0 &&  remainingWorkSecTime == 0 && remainingRestMinTime == 0 && remainingRestSecTime == 1) {//check

                    remainingWorkMinTime = initialWorkMinTime;
                    remainingWorkSecTime = initialWorkSecTime;
                    remainingRestMinTime = initialRestMinTime;
                    remainingRestSecTime = initialRestSecTime;

                    remainingSets--;
                   try{
                       Thread.sleep(1000);
                   }catch (InterruptedException e){

                   }
                    handler.sendEmptyMessage(MSG_SETVIEW_CHANGED);

                }


            }

        }

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case MSG_WORK_SEC_CHANGED:
                        txtWorkSec.setText(String.valueOf(remainingWorkSecTime));
                        break;
                    case MSG_WORK_MINSEC_CHANGED:
                        txtWorkMin.setText(String.valueOf(remainingWorkMinTime));
                        txtWorkSec.setText(String.valueOf(remainingWorkSecTime));
                        break;
                    case MSG_REST_SEC_CHANGED:
                        txtRestSec.setText(String.valueOf(remainingRestSecTime));
                        break;
                    case MSG_REST_MINSEC_CHANGED:
                        txtRestMin.setText(String.valueOf(remainingRestMinTime));
                        txtRestSec.setText(String.valueOf(remainingRestSecTime));
                        break;
                    case MSG_SET_CHANGED:
                        txtSets.setText(String.valueOf(remainingSets));
                        txtWorkMin.setText(String.valueOf(remainingWorkMinTime));
                        txtWorkSec.setText(String.valueOf(remainingWorkSecTime));
                        txtRestMin.setText(String.valueOf(remainingRestMinTime));
                        txtRestSec.setText(String.valueOf(remainingRestSecTime));
                        break;
                    case MSG_SEE_WORKVIEW:
                        workTimeView.setVisibility(View.VISIBLE);
                        restTimeView.setVisibility(View.INVISIBLE);
                        break;
                    case MSG_SEE_RESTVIEW:
                        workTimeView.setVisibility(View.INVISIBLE);
                        restTimeView.setVisibility(View.VISIBLE);
                        break;
                    case MSG_SETVIEW_CHANGED:
                        workTimeView.setVisibility(View.VISIBLE);
                        restTimeView.setVisibility(View.INVISIBLE);
                        txtSets.setText(String.valueOf(remainingSets));
                        txtWorkMin.setText(String.valueOf(remainingWorkMinTime));
                        txtWorkSec.setText(String.valueOf(remainingWorkSecTime));
                        txtRestMin.setText(String.valueOf(remainingRestMinTime));
                        txtRestSec.setText(String.valueOf(remainingRestSecTime));
                        break;
                    default:
                        Toast.makeText(playActivity.this, "Unexpected error occured", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };


    }

}


