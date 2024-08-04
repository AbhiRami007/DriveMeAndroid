package com.example.drivemeandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.adapters.DriverAdapter;
import com.example.drivemeandroid.models.Driver;
import com.google.android.material.navigation.NavigationView;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MapView map;
    private Dialog dialog;
    private TextView cancelButton;
    private Button scheduleNow;
    private Button NextButton;
    private TextView selectDayButton;
    private TextView selectTimeButton;
    private Spinner vehicleModelSpinner;
    private Spinner vehicleShiftSpinner;
    private EditText instructionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        map = findViewById(R.id.map);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(9.0);
        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);
        mapController.setCenter(startPoint);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("Pickup");
        map.getOverlays().add(startMarker);

        scheduleNow = findViewById(R.id.schedule_now);
        scheduleNow.setOnClickListener(v -> showDialog());

        ImageButton hamburgerButton = findViewById(R.id.hamburger_button);
        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_bookings) {
                Intent intent = new Intent(this, BookingsActivity.class);
                startActivity(intent);
                return true;
            }
            else if (itemId == R.id.nav_profile) {
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_settings) {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }else if (itemId == R.id.nav_payments) {
                Intent intent = new Intent(this, PaymentsActivity.class);
                startActivity(intent);
                return true;

            }else if (itemId == R.id.nav_logout) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            }

        drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.ride_schedule_modal);

        vehicleModelSpinner = dialog.findViewById(R.id.vehicleModelSpinner);
        vehicleShiftSpinner = dialog.findViewById(R.id.vehicleShiftSpinner);
        selectDayButton = dialog.findViewById(R.id.selectDayButton);
        selectTimeButton = dialog.findViewById(R.id.selectTimeButton);
        instructionInput = dialog.findViewById(R.id.instructionInput);
        cancelButton = dialog.findViewById(R.id.cancelButton);
        NextButton = dialog.findViewById(R.id.nextButton);

        ArrayAdapter<CharSequence> modelAdapter = ArrayAdapter.createFromResource(this,
                R.array.vehicle_models, android.R.layout.simple_spinner_item);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleModelSpinner.setAdapter(modelAdapter);

        ArrayAdapter<CharSequence> shiftAdapter = ArrayAdapter.createFromResource(this,
                R.array.vehicle_shifts, android.R.layout.simple_spinner_item);
        shiftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleShiftSpinner.setAdapter(shiftAdapter);

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        NextButton.setOnClickListener(v -> showCustomDialog());

        selectDayButton.setOnClickListener(v -> {
            // Show DatePickerDialog
        });

        selectTimeButton.setOnClickListener(v -> {
            // Show TimePickerDialog
        });

        dialog.show();
    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.choose_driver_modal);

        TextView cancelButton = dialog.findViewById(R.id.cancelButton);
        RecyclerView recyclerView = dialog.findViewById(R.id.driversRecyclerView);
        Button bookNowButton = dialog.findViewById(R.id.bookNowButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DriverAdapter adapter = new DriverAdapter(getDummyDrivers());
        recyclerView.setAdapter(adapter);

        cancelButton.setOnClickListener(v -> dialog.dismiss());
        bookNowButton.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }


    private List<Driver> getDummyDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("John Doe", "20 USD", "https://example.com/driver1.jpg"));
        drivers.add(new Driver("Jane Smith", "25 USD", "https://example.com/driver2.jpg"));
        drivers.add(new Driver("Michael Brown", "30 USD", "https://example.com/driver3.jpg"));
        return drivers;
    }


    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}
