package com.example.seongu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText setNumberView = null;
    EditText workMinNumberView= null;
    EditText workSecNumberView= null;
    EditText restMinNumberView= null;
    EditText restSecNumberView= null;
    ImageView playButton;

    int setCount = 0;
    int workMinCount = 0;
    int workSecCount = 0;
    int restMinCount = 0;
    int restSecCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNumberView = (EditText) findViewById(R.id.setNumber);
        workMinNumberView = (EditText) findViewById(R.id.workMinNumber);
        workSecNumberView = (EditText) findViewById(R.id.workSecNumber);
        restMinNumberView = (EditText) findViewById(R.id.restMinNumber);
        restSecNumberView = (EditText) findViewById(R.id.restSecNumber);

        Button setPlusButton = (Button) findViewById(R.id.setPlusButton);
        Button setMinusButton = (Button) findViewById(R.id.setMinusButton);
        Button workTimePlusButton = (Button) findViewById(R.id.workTimePlusButton);
        Button workTimeMinusButton = (Button) findViewById(R.id.workTimeMinusButton);
        Button restTimePlusButton = (Button) findViewById(R.id.restTimePlusButton);
        Button restTimeMinusButton = (Button) findViewById(R.id.restTimeMinusButton);

        setPlusButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                setCount = Integer.parseInt(setNumberView.getText().toString());
                setCount++;
                setNumberView.setText(String.valueOf(setCount));
            }
        });

        setMinusButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (Integer.parseInt(setNumberView.getText().toString()) == 1) {
                    ;
                } else {
                    setCount = Integer.parseInt(setNumberView.getText().toString());
                    setCount--;
                    setNumberView.setText(String.valueOf(setCount));
                }

            }
        });

        workTimePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                workSecCount = Integer.parseInt(workSecNumberView.getText().toString());
                workMinCount = Integer.parseInt(workMinNumberView.getText().toString());
                workSecCount++;
                if (workSecCount != 60) {
                    workSecNumberView.setText(String.valueOf(workSecCount));
                } else if (workSecCount == 60) {
                    workSecCount = 0;
                    workMinCount++;
                    workMinNumberView.setText(String.valueOf(workMinCount));
                    workSecNumberView.setText(String.valueOf(workSecCount));
                }
            }
        });

        workTimeMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workSecCount = Integer.parseInt(workSecNumberView.getText().toString());
                workMinCount = Integer.parseInt(workMinNumberView.getText().toString());

                if (workMinCount == 0 &&workSecCount == 1) {

                }
                else if (workMinCount ==0 && workSecCount >=2){
                    workSecCount--;
                    workSecNumberView.setText(String.valueOf(workSecCount));
                }
                else if(workMinCount !=0 && workSecCount >=1) {
                    workSecCount--;
                    workSecNumberView.setText(String.valueOf(workSecCount));
                }

                else if (workMinCount != 0 && workSecCount ==0) {
                    workMinCount--;
                    workSecCount = 59;

                    workMinNumberView.setText(String.valueOf(workMinCount));
                    workSecNumberView.setText(String.valueOf(workSecCount));
                }
            }
        });

        restTimePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restSecCount = Integer.parseInt(restSecNumberView.getText().toString());
                restMinCount = Integer.parseInt(restMinNumberView.getText().toString());
                restSecCount++;
                if (restSecCount != 60) {
                    restSecNumberView.setText(String.valueOf(restSecCount));
                } else if (restSecCount == 60) {
                    restSecCount = 0;
                    restMinCount++;
                    restMinNumberView.setText(String.valueOf(restMinCount));
                    restSecNumberView.setText(String.valueOf(restSecCount));
                }
            }
        });

        restTimeMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restSecCount = Integer.parseInt(restSecNumberView.getText().toString());;
                restMinCount = Integer.parseInt(restMinNumberView.getText().toString());
                restSecCount--;
                if (restSecCount != -1) {
                    restSecNumberView.setText(String.valueOf(restSecCount));
                } else if (restSecCount == -1 && restMinCount != 0) {
                    restMinCount--;
                    restSecCount = 59;

                    restMinNumberView.setText(String.valueOf(restMinCount));
                    restSecNumberView.setText(String.valueOf(restSecCount));
                } else if (restSecCount == -1 && restMinCount == 0) {
                    restSecCount = 0;
                    restSecNumberView.setText(String.valueOf(restSecCount));
                }
            }
        });

        setNumberView.addTextChangedListener(new setWatcher());
        workMinNumberView.addTextChangedListener(new workMinWatcher());
        workSecNumberView.addTextChangedListener(new workSecWatcher());
        restMinNumberView.addTextChangedListener(new restMinrWatcher());
        restSecNumberView.addTextChangedListener(new restSecWatcher());

        playButton = (ImageView) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sets = Integer.parseInt(setNumberView.getText().toString());
                int workMinTime = Integer.parseInt(workMinNumberView.getText().toString());
                int workSecTime = Integer.parseInt(workSecNumberView.getText().toString());
                int restMinTime = Integer.parseInt(restMinNumberView.getText().toString());
                int restSecTime = Integer.parseInt(restSecNumberView.getText().toString());

                if((Integer)sets == null) {
                    Toast.makeText(MainActivity.this, "sets are null", Toast.LENGTH_LONG).show();

                }
                else {


                    // Construct Data Object
                    Data data = new Data(sets, workMinTime, workSecTime, restMinTime, restSecTime);

                    // Store Data Object in Intent
                    Intent intent = new Intent(MainActivity.this, playActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }
            }
        });
    }

    class setWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        public void afterTextChanged(Editable s) {
            if(String.valueOf(s).matches("")) {
                setCount = 10;
            }
            else {
                try {
                    setCount = Integer.parseInt(String.valueOf(s));


                } catch (NullPointerException e) {
                }
            }
        }
    }

    class workMinWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        public void afterTextChanged(Editable s) {
            try{
                workMinCount= Integer.parseInt(String.valueOf(s));
            }catch(NullPointerException e){
            }
        }
    }

    class workSecWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        public void afterTextChanged(Editable s) {
            try{
                workSecCount= Integer.parseInt(String.valueOf(s));
            }catch(NullPointerException e){
            }
        }
    }

    class restMinrWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        public void afterTextChanged(Editable s) {
            try{
                restMinCount= Integer.parseInt(String.valueOf(s));
            }catch(NullPointerException e){
            }
        }
    }

   class restSecWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        public void afterTextChanged(Editable s) {
            try{
                restSecCount= Integer.parseInt(String.valueOf(s));
            }catch(NullPointerException e){
            }
        }
    }
}
