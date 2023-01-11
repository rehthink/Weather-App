package com.test.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.test.weather.Model.CurrentWeatherModel;
import com.test.weather.Network.RetrofitClient;
import com.test.weather.Utils.SessionManagers;
import com.test.weather.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;

    ActivityMainBinding binding;
    private static final int PERMISSION_REQUEST_CODE = 0;
    private int locationRequestCode = 1000;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    SessionManagers sessionManagers = new SessionManagers(MainActivity.this);
    LocationManager locationManager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        binding.progressHorizontal.setVisibility(View.VISIBLE);

        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressHorizontal.setVisibility(View.VISIBLE);
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (!sessionManagers.getServerResponse("temperature").isEmpty()) {

            binding.main.setText(sessionManagers.getServerResponse("main"));
            binding.title.setText(sessionManagers.fetchLocationName(getApplicationContext()));
            binding.humidity.setText("Humidity : "+sessionManagers.getServerResponse("humidity"));
            binding.wind.setText("Wind : " + sessionManagers.getServerResponse("wind_speed"));

            binding.temp.setText(kelvinToCelsius(Double.parseDouble(sessionManagers.getServerResponse("temperature"))) + " \u2103");
            binding.feelsLike.setText("Feels Like : " + kelvinToCelsius(Double.parseDouble(sessionManagers.getServerResponse("feels_like"))) + " \u2103");
            binding.tempMin.setText("Min Temp : " + kelvinToCelsius(Double.parseDouble(sessionManagers.getServerResponse("min_temp")))+" \u2103");
            binding.tempMax.setText("Max Temp : " + kelvinToCelsius(Double.parseDouble(sessionManagers.getServerResponse("max_temp")))+ " \u2103");

            binding.city1.setText(sessionManagers.getServerResponse("city1"));
            binding.city2.setText(sessionManagers.getServerResponse("city2"));
            binding.temp1.setText(sessionManagers.getServerResponse("temperature1"));
            binding.temp2.setText(sessionManagers.getServerResponse("temperature2"));
        }

        getForecast();
        initCurrentWeather(sessionManagers.fetchLocationName(MainActivity.this));
        initCurrentWeather2("Dubai");
        initCurrentWeather3("Berlin");
    }

    public void initCurrentWeather(String city) {
        Call<CurrentWeatherModel> call = RetrofitClient.getInstance().getMyApi().getCurrentWeather(city, "38a375e15945c5cd466f5d953b85c0e0");
        call.enqueue(new Callback<CurrentWeatherModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CurrentWeatherModel> call, Response<CurrentWeatherModel> response) {
                CurrentWeatherModel currentWeatherModel = response.body();

                if (response.isSuccessful()) {
                    binding.progressHorizontal.setVisibility(View.GONE);
                    Log.e("TAG", response.message() + " Ok");
                    Log.e("TAG", currentWeatherModel.getName());

                    binding.title.setText(currentWeatherModel.getName());
                    binding.main.setText(currentWeatherModel.getWeather().get(0).getMain());

                    sessionManagers.saveServerResponse("main", currentWeatherModel.getWeather().get(0).getMain());
                    sessionManagers.saveServerResponse("description", currentWeatherModel.getWeather().get(0).getDescription());
                    sessionManagers.saveServerResponse("temperature", String.valueOf(currentWeatherModel.getMain().getTemp()));
                    sessionManagers.saveServerResponse("feels_like", String.valueOf(currentWeatherModel.getMain().getFeels_like()));
                    sessionManagers.saveServerResponse("min_temp", String.valueOf(currentWeatherModel.getMain().getTemp_min()));
                    sessionManagers.saveServerResponse("max_temp", String.valueOf(currentWeatherModel.getMain().getTemp_max()));
                    sessionManagers.saveServerResponse("wind_speed", String.valueOf(currentWeatherModel.getWind().getSpeed()));
                    sessionManagers.saveServerResponse("humidity", String.valueOf(currentWeatherModel.getMain().getHumidity()));

                    binding.humidity.setText("Humidity : "+currentWeatherModel.getMain().getHumidity());
                    binding.wind.setText("Wind : " + currentWeatherModel.getWind().getSpeed());
                    binding.temp.setText(kelvinToCelsius(currentWeatherModel.getMain().getTemp()) + " \u2103");
                    binding.feelsLike.setText("Feels Like : " + kelvinToCelsius(currentWeatherModel.getMain().getFeels_like()) + " \u2103");
                    binding.tempMin.setText("Min Temp : " + kelvinToCelsius(currentWeatherModel.getMain().getTemp_min())+" \u2103");
                    binding.tempMax.setText("Max Temp : " + kelvinToCelsius(currentWeatherModel.getMain().getTemp_max())+ " \u2103");
                } else {
                    binding.progressHorizontal.setVisibility(View.GONE);
                    Log.e("TAG", response.message() + " Ok " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherModel> call, Throwable t) {
                binding.progressHorizontal.setVisibility(View.GONE);
                Log.e("TAG", t.getMessage());
            }
        });
    }

    public void initCurrentWeather2(String city) {
        Call<CurrentWeatherModel> call = RetrofitClient.getInstance().getMyApi().getCurrentWeather(city, "38a375e15945c5cd466f5d953b85c0e0");
        call.enqueue(new Callback<CurrentWeatherModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CurrentWeatherModel> call, Response<CurrentWeatherModel> response) {
                CurrentWeatherModel currentWeatherModel = response.body();

                if (response.isSuccessful()) {
                    binding.progressHorizontal.setVisibility(View.GONE);
                    Log.e("TAG", response.message() + " Ok");
                    Log.e("TAG", currentWeatherModel.getName());

                    sessionManagers.saveServerResponse("temperature1", String.valueOf(currentWeatherModel.getMain().getTemp()));
                    sessionManagers.saveServerResponse("city1", String.valueOf(currentWeatherModel.getName()));

                    binding.temp1.setText(kelvinToCelsius(currentWeatherModel.getMain().getTemp()) + " \u2103");
                    binding.city1.setText(currentWeatherModel.getName());

                } else {
                    binding.progressHorizontal.setVisibility(View.GONE);
                    Log.e("TAG", response.message() + " Ok " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherModel> call, Throwable t) {
                binding.progressHorizontal.setVisibility(View.GONE);
                Log.e("TAG", t.getMessage());
            }
        });
    }

    public void initCurrentWeather3(String city) {
        Call<CurrentWeatherModel> call = RetrofitClient.getInstance().getMyApi().getCurrentWeather(city, "38a375e15945c5cd466f5d953b85c0e0");
        call.enqueue(new Callback<CurrentWeatherModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CurrentWeatherModel> call, Response<CurrentWeatherModel> response) {
                CurrentWeatherModel currentWeatherModel = response.body();

                if (response.isSuccessful()) {
                    binding.progressHorizontal.setVisibility(View.GONE);
                    Log.e("TAG", response.message() + " Ok");
                    Log.e("TAG", currentWeatherModel.getName());

                    sessionManagers.saveServerResponse("temperature2", String.valueOf(currentWeatherModel.getMain().getTemp()));
                    sessionManagers.saveServerResponse("city2", String.valueOf(currentWeatherModel.getName()));

                    binding.temp2.setText(kelvinToCelsius(currentWeatherModel.getMain().getTemp()) + " \u2103");
                    binding.city2.setText(currentWeatherModel.getName());

                } else {
                    binding.progressHorizontal.setVisibility(View.GONE);
                    Log.e("TAG", response.message() + " Ok " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherModel> call, Throwable t) {
                binding.progressHorizontal.setVisibility(View.GONE);
                Log.e("TAG", t.getMessage());
            }
        });
    }

    private double kelvinToCelsius(double temp){
        double celsius;

        celsius = temp - 273.15;
        String celc = String.format("%.2f", celsius);
        celsius = Double.parseDouble(celc);

        return celsius;
    }

    private void getForecast() {
        Log.e("TAG L", "ok");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Log.e("TAG L", "okk");
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                            Log.e("TAG L", String.valueOf(addresses.get(0).getLatitude()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Log.e("TAG L", "Null");
                    }
                }
            });
        }else {
            Log.e("TAG L", "oops!");
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getForecast();
            }else {
                Toast.makeText(this, "Stormy requires your location to function. Stormy won't ask for location permissions again, but you can always enable the permission in settings", Toast.LENGTH_LONG).show();
            }
        }
    }

}