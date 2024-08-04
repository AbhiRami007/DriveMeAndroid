package com.example.drivemeandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText editName, editTag, editAbout, editModel, editShift;
    private TextView walletBalance;
    private Button editButton, saveButton, rechargeButton;
    private boolean isEditing = false;
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Views
        editName = findViewById(R.id.edit_name);
        editTag = findViewById(R.id.edit_tag);
        editAbout = findViewById(R.id.edit_about);
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
        // Dummy Data
        setDummyData();

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

    private void setDummyData() {
        // Set dummy data
        editName.setText("John Doe");
        editTag.setText("Driver");
        editAbout.setText("Experienced driver with 5 years of experience.");
        editModel.setText("Toyota Corolla");
        editShift.setText("Morning");
        walletBalance.setText("1000");
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
