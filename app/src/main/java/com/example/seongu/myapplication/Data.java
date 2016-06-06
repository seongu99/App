package com.example.seongu.myapplication;

import java.io.Serializable;

/**
 * Created by seongu on 5/31/2016.
 */
public class Data implements Serializable {

    public int sets;
    public int workMinTime;
    public int workSecTime;
    public int restMinTime;
    public int restSecTime;

    public Data(int sets, int workMinTime, int workSecTime, int restMinTime, int restSecTime) {
        this.sets = sets;
        this.workMinTime = workMinTime;
        this.workSecTime = workSecTime;
        this.restMinTime = restMinTime;
        this.restSecTime = restSecTime;
    }
}
