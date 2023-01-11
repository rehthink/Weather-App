package com.test.weather.Model;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("deg") int deg;
    @SerializedName("speed") double speed;

    public int getDeg() {
        return deg;
    }

    public double getSpeed() {
        return speed;
    }
}
