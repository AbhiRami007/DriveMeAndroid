package com.example.drivemeandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivemeandroid.R;
import com.example.drivemeandroid.models.Booking;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private Context context;
    private List<Booking> bookingList;

    public BookingAdapter(Context context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bookings, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.driverNameTextView.setText(booking.getDriverName());

        Picasso.get().load(booking.getProfileImageUrl()).into(holder.driverImageView);

        switch (booking.getBookingStatus()) {
            case 0:
                holder.statusTextView.setText("Pending");
                holder.statusTextView.setTextAppearance(context, R.style.statusPending);
                break;
            case 1:
                holder.statusTextView.setText("Available");
                holder.statusTextView.setTextAppearance(context, R.style.statusAvailable);
                break;
            default:
                holder.statusTextView.setText("Rejected");
                holder.statusTextView.setTextAppearance(context, R.style.statusRejected);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        ImageView driverImageView;
        TextView driverNameTextView;
        TextView statusTextView;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            driverImageView = itemView.findViewById(R.id.driverImageView);
            driverNameTextView = itemView.findViewById(R.id.driverNameTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }
}
