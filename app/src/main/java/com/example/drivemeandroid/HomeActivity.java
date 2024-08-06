package com.example.drivemeandroid;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.adapters.DriverAdapter;
import com.example.drivemeandroid.models.Driver;
import com.example.drivemeandroid.models.RideSchedule;
import com.example.drivemeandroid.models.UserDetails;
import com.example.drivemeandroid.models.UserVehicle;
import com.google.android.material.navigation.NavigationView;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.Date;
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
    private EditText pickUp, dropOff;

    private AppDatabase database;
    private int selectedDriverId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        database = AppDatabase.getInstance(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        pickUp = findViewById(R.id.pickupText);
        dropOff = findViewById(R.id.dropoffText);
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

        NextButton.setOnClickListener(v ->
        {
            showCustomDialog();
            dialog.dismiss();
        });

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
        DriverAdapter adapter = new DriverAdapter(getDummyDrivers(), driverId -> selectedDriverId = driverId);
        recyclerView.setAdapter(adapter);

        cancelButton.setOnClickListener(v -> dialog.dismiss());
        bookNowButton.setOnClickListener(v -> {
            saveBookingDetails();
        });

        dialog.show();
    }

    private void saveBookingDetails() {
        // Retrieve data from UI
        String pickUpLocation = pickUp.getText().toString();
        String dropOffLocation = dropOff.getText().toString();
        String vehicleModel = vehicleModelSpinner.getSelectedItem().toString();
        String vehicleShift = vehicleShiftSpinner.getSelectedItem().toString();
        String instructions = instructionInput.getText().toString();
        String date = new Date().toString();
        String time = new Date().toString();
        int driverId = selectedDriverId;
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        RideSchedule rideSchedule = new RideSchedule();
        rideSchedule.setDate(date);
        rideSchedule.setTime(time);
        rideSchedule.setDriverId(driverId);
        rideSchedule.setDropOffLocation(dropOffLocation);
        rideSchedule.setPickUpLocation(pickUpLocation);
        rideSchedule.setBookingStatus(0);
        rideSchedule.setInstructions(instructions);
        rideSchedule.setPassengerId(userId);

        UserVehicle userVehicles = new UserVehicle();
        userVehicles.setModel(vehicleModel);
        userVehicles.setShift(vehicleShift);
        userVehicles.setPassengerId(userId);
        Intent intent = new Intent(HomeActivity.this, BookingSuccessActivity.class);
        intent.putExtra("pickUp", pickUpLocation);
        intent.putExtra("dropOff", dropOffLocation);
        startActivity(intent);
        new Thread(() -> {
            database.rideScheduleDao().insertRideSchedule(rideSchedule);
            database.userVehicleDao().insert(userVehicles);
        }).start();

    }

    private List<Driver> getDummyDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver(1,"John Doe", 20, "https://example.com/driver1.jpg"));
        drivers.add(new Driver(2, "Jane Smith", 25, "https://example.com/driver2.jpg"));
        drivers.add(new Driver(3, "Michael Brown", 30, "https://example.com/driver3.jpg"));
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
