package com.example.drivemeandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.drivemeandroid.adapters.RequestAdapter;
import com.example.drivemeandroid.models.Driver;
import com.example.drivemeandroid.models.Request;
import com.example.drivemeandroid.models.RideSchedule;
import com.example.drivemeandroid.models.UserDetails;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RequestsActivity extends AppCompatActivity {

    private ListView listViewRequests;
    private RequestAdapter requestAdapter;
    private ArrayList<Request> requests;
    private ImageView backButton;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        ImageButton hamburgerButton = findViewById(R.id.hamburger_button);
        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        database = AppDatabase.getInstance(this);

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
        listViewRequests = findViewById(R.id.listViewRequests);

        loadRequests();
    }

    private void loadRequests() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);
        String charge = sharedPreferences.getString("charge", "");
        ArrayList<Request> requests = new ArrayList<>();
        new Thread(() -> {
            List<RideSchedule> schedule = database.rideScheduleDao().getRideScheduleByDriverId(userId);
            for (RideSchedule rideSchedule : schedule) {
                UserDetails passenger = database.userDao().getUser(rideSchedule.getPassengerId());
                requests.add(new Request(passenger.getName(), passenger.getEmail(), rideSchedule.getPickUpLocation(), rideSchedule.getDropOffLocation(), charge, rideSchedule.getRideId(), rideSchedule.getBookingStatus()));
            }
        }).start();
        requests.sort((b1, b2) -> Integer.compare(b2.getRideId(), b1.getRideId()));;
        requestAdapter = new RequestAdapter(this, requests, database);
        listViewRequests.setAdapter(requestAdapter);
    }
}
