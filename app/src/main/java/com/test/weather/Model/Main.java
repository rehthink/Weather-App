package com.test.weather.Model;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("feels_like") double feels_like;
    @SerializedName("humidity") int humidity;
    @SerializedName("pressure") int pressure;
    @SerializedName("temp") double temp;
    @SerializedName("temp_max") double temp_max;
    @SerializedName("temp_min") double temp_min;

    public double getFeels_like() {
        return feels_like;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public double getTemp() {
        return temp;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }
}
