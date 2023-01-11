package com.test.weather.Network;

import com.test.weather.Model.CurrentWeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Call<CurrentWeatherModel> getCurrentWeather(@Query("q") String city,
                                                @Query("appid") String appId);

}
