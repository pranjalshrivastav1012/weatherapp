package com.pranjal.sri.weatherapp.api.response;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private float lon;
    private float lat;

    public Coordinate(){

        this.lon= lon;
        this.lat= lat;
    }

    public Coordinate(float lon, float lat){
        this.lon= lon;
        this.lat= lat;

    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
