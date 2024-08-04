package com.example.drivemeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.R;
import com.example.drivemeandroid.adapters.BookingAdapter;
import com.example.drivemeandroid.models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        List<Booking> dummyBookings = createBookings();
        bookingAdapter = new BookingAdapter(this, dummyBookings);
        recyclerView.setAdapter(bookingAdapter);
    }

    private List<Booking> createBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking("John Doe", "https://example.com/john_doe.jpg", 20.0, 1));
        bookings.add(new Booking("Jane Smith", "https://example.com/jane_smith.jpg", 25.0, 0));
        bookings.add(new Booking("Alice Johnson", "https://example.com/alice_johnson.jpg", 30.0, 2));
        return bookings;
    }
}
