package com.example.drivemeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.adapters.BookingAdapter;
import com.example.drivemeandroid.models.Booking;
import com.example.drivemeandroid.models.RideSchedule;
import com.example.drivemeandroid.models.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class BookingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private ImageView backButton;
    private TextView noBookingsMessage;
    private AppDatabase database;
    private List<Booking> bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings);

        database = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backButton = findViewById(R.id.back_button);
        noBookingsMessage = findViewById(R.id.no_bookings_message);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(BookingsActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // Initialize the bookings list
        bookings = new ArrayList<>();
        getBookings();
    }

    private void getBookings() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        new Thread(() -> {
            List<RideSchedule> schedule = database.rideScheduleDao().getRideScheduleById(userId);
            List<Booking> bookingsList = new ArrayList<>();

            for (RideSchedule rideSchedule : schedule) {
                UserDetails user = database.userDao().getUser(rideSchedule.getDriverId());
                String driverName = user.getName();
                int bookingStatus = rideSchedule.getBookingStatus();

                Booking booking = new Booking(driverName, bookingStatus);
                bookingsList.add(booking);
            }

            // Update the UI on the main thread
            new Handler(Looper.getMainLooper()).post(() -> {
                if (bookingsList.isEmpty()) {
                    noBookingsMessage.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    noBookingsMessage.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    bookingAdapter = new BookingAdapter(this, bookingsList);
                    recyclerView.setAdapter(bookingAdapter);
                }
            });
        }).start();
    }
}
