package com.test.weather.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.test.weather.MainActivity;
import com.test.weather.R;
import com.test.weather.Utils.SessionManagers;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    List<String> list;
    private Context context;
    SessionManagers sessionManagers;

    public LocationAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem =layoutInflater.inflate(R.layout.location_item, parent, false);
        return new LocationAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sessionManagers = new SessionManagers(context);
        holder.location.setText(list.get(holder.getAdapterPosition()));
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sessionManagers.saveLocationName(context, list.get(holder.getAdapterPosition()));
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView location;
        CardView click;
        public ViewHolder(View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.location_name);
            click = itemView.findViewById(R.id.location_card);

        }
    }
}
