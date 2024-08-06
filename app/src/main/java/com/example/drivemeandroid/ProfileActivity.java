package com.example.drivemeandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drivemeandroid.adapters.BookingAdapter;
import com.example.drivemeandroid.models.Booking;
import com.example.drivemeandroid.models.RideSchedule;
import com.example.drivemeandroid.models.UserDetails;
import com.example.drivemeandroid.models.UserVehicle;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private EditText editName, editTag, editAbout, editModel, editShift;
    private TextView walletBalance;
    private Button editButton, saveButton, rechargeButton;
    private boolean isEditing = false;
    private ImageView backButton;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        database = AppDatabase.getInstance(this);
        // Initialize Views
        editName = findViewById(R.id.edit_name);
        editTag = findViewById(R.id.edit_tag);
        editModel = findViewById(R.id.edit_model);
        editShift = findViewById(R.id.edit_shift);
        walletBalance = findViewById(R.id.wallet_balance);
        editButton = findViewById(R.id.edit_button);
        saveButton = findViewById(R.id.save_button);
        rechargeButton = findViewById(R.id.recharge_button);
        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        setProfileData();

        // Edit Button Click Listener
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEditMode();
            }
        });

        // Save Button Click Listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        // Recharge Button Click Listener
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRechargeDialog();
            }
        });
    }

    private void setProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        new Thread(() -> {
            UserDetails userDetails = database.userDao().getUser(userId);
            UserVehicle userVehicle = database.userVehicleDao().getVehicleByUserId(userId);
            editName.setText(userDetails.getName());
            editTag.setText(userDetails.getUserRole());
            editModel.setText(userVehicle.getModel());
            editShift.setText(userVehicle.getShift());
            walletBalance.setText(userDetails.getWalletBalance());
        }).start();
    }

    private void toggleEditMode() {
        isEditing = !isEditing;
        editName.setEnabled(isEditing);
        editTag.setEnabled(isEditing);
        editAbout.setEnabled(isEditing);
        editModel.setEnabled(isEditing);
        editShift.setEnabled(isEditing);
        editButton.setVisibility(isEditing ? View.GONE : View.VISIBLE);
        saveButton.setVisibility(isEditing ? View.VISIBLE : View.GONE);
    }

    private void saveProfile() {
        // Save profile data
        // Here you can add code to update the profile data
        toggleEditMode();
    }

    private void showRechargeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recharge Wallet");

        // Set up the input fields
        final EditText inputAmount = new EditText(this);
        inputAmount.setHint("Enter amount");
        builder.setView(inputAmount);

        builder.setPositiveButton("Recharge", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle recharge
                String amount = inputAmount.getText().toString();
                handleRecharge(amount);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void handleRecharge(String amount) {
        // Handle wallet recharge logic
        // Update the wallet balance
        int currentBalance = Integer.parseInt(walletBalance.getText().toString());
        int rechargeAmount = Integer.parseInt(amount);
        walletBalance.setText(String.valueOf(currentBalance + rechargeAmount));
    }
}
