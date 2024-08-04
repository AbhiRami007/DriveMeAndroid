package com.example.drivemeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        TextView titleText = findViewById(R.id.title_text);
        ImageView driverImage = findViewById(R.id.driver_image);
        ImageView passengerImage = findViewById(R.id.passenger_image);

        driverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelectRole("Driver");
            }
        });

        passengerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelectRole("Passenger");
            }
        });
    }

    private void handleSelectRole(String role) {
        Intent intent = new Intent(ProfileSelectionActivity.this, SignUpActivity.class);
        intent.putExtra("userRole", role);
        startActivity(intent);
    }
}
