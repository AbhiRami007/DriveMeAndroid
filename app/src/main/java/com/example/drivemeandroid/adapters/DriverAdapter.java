package com.example.drivemeandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.R;
import com.example.drivemeandroid.models.Driver;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    private List<Driver> driverList;

    public DriverAdapter(List<Driver> driverList) {
        this.driverList = driverList;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_card, parent, false);
        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        Driver driver = driverList.get(position);
        holder.textViewDriverName.setText(driver.getName());
        holder.textViewDriverRate.setText("Rate: " + driver.getRatePerHour());

        // Load image with Picasso
        Picasso.get()
                .load(driver.getImageUrl())
                .placeholder(R.drawable.profile) // Default image if URL is null or image loading fails
                .error(R.drawable.profile)       // Default image if there's an error loading the image
                .into(holder.imageViewDriver);
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public static class DriverViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDriver;
        TextView textViewDriverName, textViewDriverRate;

        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewDriver = itemView.findViewById(R.id.imageViewDriver);
            textViewDriverName = itemView.findViewById(R.id.textViewDriverName);
            textViewDriverRate = itemView.findViewById(R.id.textViewDriverRate);
        }
    }
}
