package com.pranjal.sri.weatherapp.api.response;

import java.io.Serializable;

public class DataResponse implements Serializable {
    private Coordinate coord;
    private Weather[] weather;
    private String base;
    private MainWeather main;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private CountrySystem sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}
