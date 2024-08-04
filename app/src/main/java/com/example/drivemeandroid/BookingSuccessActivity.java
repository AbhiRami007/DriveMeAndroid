package com.example.drivemeandroid;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.adapters.DriverAdapter;
import com.example.drivemeandroid.models.Driver;

import java.util.ArrayList;
import java.util.List;

public class BookingSuccessActivity extends AppCompatActivity {

    private TextView textBookingMessage, textPickupLocation, textDropoffLocation, textBookingStatus;
    private ImageView imageBookingSuccess;
    private RecyclerView recyclerViewDrivers;
    private DriverAdapter driverAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);

        imageBookingSuccess = findViewById(R.id.imageViewSuccess);
        textBookingMessage = findViewById(R.id.textViewMessage);
        textPickupLocation = findViewById(R.id.textViewPickupLocation);
        textDropoffLocation = findViewById(R.id.textViewDropoffLocation);
        textBookingStatus = findViewById(R.id.textViewBookingStatus);
        recyclerViewDrivers = findViewById(R.id.recyclerViewDrivers);

        // Set demo data
        textPickupLocation.setText("123 Main Street");
        textDropoffLocation.setText("456 Elm Street");
        textBookingStatus.setText("Waiting for Confirmation");

        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("John Doe", "https://example.com/john_doe.jpg", "$20/hour"));
        drivers.add(new Driver("Jane Smith", "https://example.com/jane_smith.jpg", "$25/hour"));

        driverAdapter = new DriverAdapter(drivers);
        recyclerViewDrivers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDrivers.setAdapter(driverAdapter);
    }
}
