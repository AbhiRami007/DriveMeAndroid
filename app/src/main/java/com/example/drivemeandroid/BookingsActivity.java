package com.example.drivemeandroid;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookings);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Booking> dummyBookings = createDummyBookings();
        bookingAdapter = new BookingAdapter(this, dummyBookings);
        recyclerView.setAdapter(bookingAdapter);
    }

    private List<Booking> createDummyBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking("John Doe", "https://example.com/john_doe.jpg", 20.0, 1));
        bookings.add(new Booking("Jane Smith", "https://example.com/jane_smith.jpg", 25.0, 0));
        bookings.add(new Booking("Alice Johnson", "https://example.com/alice_johnson.jpg", 30.0, 2));
        return bookings;
    }
}
