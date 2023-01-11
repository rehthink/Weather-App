package com.test.weather.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherModel {

    @SerializedName("base")
    String base;
    @SerializedName("clouds")
    Clouds clouds;
    @SerializedName("cod")
    int cod;
    @SerializedName("coord")
    Coord coord;
    @SerializedName("dt")
    int dt;
    @SerializedName("id")
    int id;
    @SerializedName("main")
    Main main;
    @SerializedName("name")
    String name;
    @SerializedName("sys")
    Sys sys;
    @SerializedName("timezone")
    int timezone;
    @SerializedName("visibility")
    int visibility;
    @SerializedName("weather")
    List<Weather> weather;

    @SerializedName("wind")
    Wind wind;

    public String getBase() {
        return base;
    }

    public int getCod() {
        return cod;
    }

    public int getDt() {
        return dt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTimezone() {
        return timezone;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public Main getMain() {
        return main;
    }

    public Sys getSys() {
        return sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}


