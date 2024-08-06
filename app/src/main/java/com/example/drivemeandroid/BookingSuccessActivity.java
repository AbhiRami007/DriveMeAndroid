package com.example.drivemeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.adapters.DriverAdapter;
import com.example.drivemeandroid.models.Driver;
import com.example.drivemeandroid.models.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class BookingSuccessActivity extends AppCompatActivity {

    private TextView textBookingMessage, textPickupLocation, textDropoffLocation, textBookingStatus;
    private ImageView imageBookingSuccess;
    private RecyclerView recyclerViewDrivers;
    private DriverAdapter driverAdapter;
    private Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);
        goBack = findViewById(R.id.buttonGoBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingSuccessActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String pickUp = getIntent().getStringExtra("pickUp");
        String dropOff = getIntent().getStringExtra("dropOff");
        textPickupLocation = findViewById(R.id.textViewPickupLocation);
        textDropoffLocation = findViewById(R.id.textViewDropoffLocation);
        textBookingStatus = findViewById(R.id.textViewBookingStatus);

        textPickupLocation.setText(pickUp);
        textDropoffLocation.setText(dropOff);
        textBookingStatus.setText("Waiting for Confirmation");
    }
}
