package com.test.weather.Model;

import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("country") String country;
    @SerializedName("id") int id;
    @SerializedName("sunrise") int sunrise;
    @SerializedName("sunset") int sunset;
    @SerializedName("type") int type;

    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public int getType() {
        return type;
    }
}
