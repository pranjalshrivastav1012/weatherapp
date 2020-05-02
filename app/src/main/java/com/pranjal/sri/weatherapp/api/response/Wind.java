package com.pranjal.sri.weatherapp.api.response;

import java.io.Serializable;

public class Wind implements Serializable {

    private float speed;
    private float degree;

    public Wind(){

        this.speed = speed;
        this.degree= degree;
    }

    public Wind(float speed,float degree){

        this.speed = speed;
        this.degree= degree;

    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
