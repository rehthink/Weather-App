package com.test.weather.Model;

import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lat") String lat;
    @SerializedName("lon") String lon;

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
