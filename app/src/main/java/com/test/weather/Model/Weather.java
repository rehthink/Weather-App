package com.test.weather.Model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("description") String description;
    @SerializedName("icon") String icon;
    @SerializedName("id") String id;
    @SerializedName("main") String main;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getMain() {
        return main;
    }
}
