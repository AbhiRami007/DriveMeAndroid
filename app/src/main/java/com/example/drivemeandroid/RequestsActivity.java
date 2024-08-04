package com.example.drivemeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.adapters.RequestAdapter;
import com.example.drivemeandroid.models.Request;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class RequestsActivity extends AppCompatActivity {

    private ListView listViewRequests;
    private RequestAdapter requestAdapter;
    private ArrayList<Request> requests;
    private ImageView backButton;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
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
        listViewRequests = findViewById(R.id.listViewRequests);

        // Initialize dummy data
        requests = new ArrayList<>();
        loadRequests();
        requestAdapter = new RequestAdapter(this, requests);
        listViewRequests.setAdapter(requestAdapter);
    }

    private void loadRequests() {
        requests.add(new Request("John Doe", "johndoe@example.com", "123 Main St", "456 Elm St", "$50"));
    }
}
