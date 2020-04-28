package com.pranjal.sri.weatherapp.api.response;

import java.io.Serializable;

public class CountrySystem implements Serializable {

    private String country;
    private int sunrise;
    private int sunset;

    public CountrySystem(){

        this.country= country;
        this.sunrise= sunrise;
        this.sunset= sunset;
    }
    public CountrySystem(String country, int sunrise, int sunset){
        this.country= country;
        this.sunrise= sunrise;
        this.sunset= sunset;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}
