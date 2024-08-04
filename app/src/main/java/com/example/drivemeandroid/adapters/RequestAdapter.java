package com.example.drivemeandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drivemeandroid.R;
import com.example.drivemeandroid.models.Request;

import java.util.ArrayList;

public class RequestAdapter extends ArrayAdapter<Request> {

    private final Context context;
    private final ArrayList<Request> requests;

    public RequestAdapter(Context context, ArrayList<Request> requests) {
        super(context, R.layout.item_request, requests);
        this.context = context;
        this.requests = requests;
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

        // Set data
        textViewName.setText(request.getName());
        textViewEmail.setText(request.getEmail());
        textViewPickupLocation.setText(request.getPickupLocation());
        textViewDropOffLocation.setText(request.getDropOffLocation());
        textViewPrice.setText(request.getPrice());

        // Handle button clicks
        buttonAccept.setOnClickListener(v -> {
            // Handle accept action
        });

        buttonReject.setOnClickListener(v -> {
            // Handle reject action
        });

        return convertView;
    }
}
