package com.pranjal.sri.weatherapp.api.response;

import java.io.Serializable;

public class Weather implements Serializable {
    private int id;
    private String main;
    private String description;
    private String icon;

    public Weather
            (){
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Weather(int id, String main, String description, String icon){
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return this.id;
    }

    public void setMain(String main){
        this.main=main;
    }

    public String getMain(){
        return this.main;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setIcon(String icon){
        this.icon=icon;
    }

    public String getIcon(){
        return this.icon;
    }
}
