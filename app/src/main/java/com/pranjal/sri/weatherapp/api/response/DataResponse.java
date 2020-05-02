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

    public DataResponse(Coordinate coord, Weather[] weather, String base, MainWeather main, Wind wind, Clouds clouds, int dt, CountrySystem sys, int timezone, int id, String name, int cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public DataResponse() {
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    public Weather[] getWeather() {
        return this.weather;
    }

    public String getBase() {
        return this.base;
    }

    public MainWeather getMain() {
        return this.main;
    }

    public Wind getWind() {
        return this.wind;
    }

    public Clouds getClouds() {
        return this.clouds;
    }

    public int getDt() {
        return this.dt;
    }

    public CountrySystem getSys() {
        return this.sys;
    }

    public int getTimezone() {
        return this.timezone;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCod() {
        return this.cod;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setMain(MainWeather main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setSys(CountrySystem sys) {
        this.sys = sys;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DataResponse)) return false;
        final DataResponse other = (DataResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$coord = this.getCoord();
        final Object other$coord = other.getCoord();
        if (this$coord == null ? other$coord != null : !this$coord.equals(other$coord))
            return false;
        if (!java.util.Arrays.deepEquals(this.getWeather(), other.getWeather())) return false;
        final Object this$base = this.getBase();
        final Object other$base = other.getBase();
        if (this$base == null ? other$base != null : !this$base.equals(other$base)) return false;
        final Object this$main = this.getMain();
        final Object other$main = other.getMain();
        if (this$main == null ? other$main != null : !this$main.equals(other$main)) return false;
        final Object this$wind = this.getWind();
        final Object other$wind = other.getWind();
        if (this$wind == null ? other$wind != null : !this$wind.equals(other$wind)) return false;
        final Object this$clouds = this.getClouds();
        final Object other$clouds = other.getClouds();
        if (this$clouds == null ? other$clouds != null : !this$clouds.equals(other$clouds))
            return false;
        if (this.getDt() != other.getDt()) return false;
        final Object this$sys = this.getSys();
        final Object other$sys = other.getSys();
        if (this$sys == null ? other$sys != null : !this$sys.equals(other$sys)) return false;
        if (this.getTimezone() != other.getTimezone()) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (this.getCod() != other.getCod()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DataResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $coord = this.getCoord();
        result = result * PRIME + ($coord == null ? 43 : $coord.hashCode());
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getWeather());
        final Object $base = this.getBase();
        result = result * PRIME + ($base == null ? 43 : $base.hashCode());
        final Object $main = this.getMain();
        result = result * PRIME + ($main == null ? 43 : $main.hashCode());
        final Object $wind = this.getWind();
        result = result * PRIME + ($wind == null ? 43 : $wind.hashCode());
        final Object $clouds = this.getClouds();
        result = result * PRIME + ($clouds == null ? 43 : $clouds.hashCode());
        result = result * PRIME + this.getDt();
        final Object $sys = this.getSys();
        result = result * PRIME + ($sys == null ? 43 : $sys.hashCode());
        result = result * PRIME + this.getTimezone();
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.getCod();
        return result;
    }

    public String toString() {
        return "DataResponse(coord=" + this.getCoord() + ", weather=" + java.util.Arrays.deepToString(this.getWeather()) + ", base=" + this.getBase() + ", main=" + this.getMain() + ", wind=" + this.getWind() + ", clouds=" + this.getClouds() + ", dt=" + this.getDt() + ", sys=" + this.getSys() + ", timezone=" + this.getTimezone() + ", id=" + this.getId() + ", name=" + this.getName() + ", cod=" + this.getCod() + ")";
    }
}
