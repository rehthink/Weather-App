package com.test.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.test.weather.Adapters.LocationAdapter;
import com.test.weather.databinding.ActivityLocationBinding;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    ActivityLocationBinding binding;
    LocationAdapter adapter;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list.add("Delhi");
        list.add("New York");
        list.add("Singapore");
        list.add("Mumbai");
        list.add("Sydney");
        list.add("Melbourne");
        setUpRequestRecyclerView(list);

    }

    private void setUpRequestRecyclerView(List<String> data) {
        adapter = new LocationAdapter(data, LocationActivity.this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(LocationActivity.this));
    }
}