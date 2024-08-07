package com.example.drivemeandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drivemeandroid.AppDatabase;
import com.example.drivemeandroid.R;
import com.example.drivemeandroid.models.Request;

import java.util.ArrayList;
import java.util.Comparator;

public class RequestAdapter extends ArrayAdapter<Request> {

    private final Context context;
    private final ArrayList<Request> requests;
    private final AppDatabase requestDatabase;

    public RequestAdapter(Context context, ArrayList<Request> requests, AppDatabase requestDatabase) {
        super(context, R.layout.item_request, requests);
        this.context = context;
        this.requests = requests;
        this.requestDatabase = requestDatabase;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_request, parent, false);
        }

        Request request = requests.get(position);

        ImageView imageViewProfile = convertView.findViewById(R.id.imageViewProfile);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        TextView textViewPickupLocation = convertView.findViewById(R.id.textViewPickupLocation);
        TextView textViewDropOffLocation = convertView.findViewById(R.id.textViewDropOffLocation);
        TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);
        Button buttonAccept = convertView.findViewById(R.id.buttonAccept);
        Button buttonReject = convertView.findViewById(R.id.buttonReject);
        TextView accepted = convertView.findViewById(R.id.accepted);
        TextView rejected = convertView.findViewById(R.id.rejected);

        // Set data
        textViewName.setText(request.getName());
        textViewEmail.setText(request.getEmail());
        textViewPickupLocation.setText(request.getPickupLocation());
        textViewDropOffLocation.setText(request.getDropOffLocation());
        textViewPrice.setText(request.getPrice());

        int status = request.getBookingStatus();
        if (status == 1 || status == 2) {
            buttonAccept.setVisibility(View.GONE);
            buttonReject.setVisibility(View.GONE);
            if (status == 1) {
                accepted.setVisibility(View.VISIBLE);
            } else {
                rejected.setVisibility(View.VISIBLE);
            }
        }

        buttonAccept.setOnClickListener(v -> {
            updateRequestStatus(request.getRideId(), 1, position);
        });

        buttonReject.setOnClickListener(v -> {
            updateRequestStatus(request.getRideId(), 2, position);
        });

        return convertView;
    }

    private void updateRequestStatus(int requestId, int status, int position) {
        new Thread(() -> {
            // Update the request status in the database
            requestDatabase.rideScheduleDao().updateRequestStatus(requestId, status);

            // Update the local list and notify the adapter on the main thread
            ((android.app.Activity) context).runOnUiThread(() -> {
                // Refresh the requests list
                requests.get(position).setBookingStatus(status);
                notifyDataSetChanged();
            });
        }).start();
    }
}
