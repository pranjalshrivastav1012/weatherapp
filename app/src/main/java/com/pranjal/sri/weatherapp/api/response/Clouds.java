package com.pranjal.sri.weatherapp.api.response;

import java.io.Serializable;

public class Clouds implements Serializable {

    private int all;

    public Clouds(){

        this.all= all;
    }
    public Clouds(int all){

        this.all=all;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
