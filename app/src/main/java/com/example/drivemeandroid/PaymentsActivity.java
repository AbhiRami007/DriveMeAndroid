package com.example.drivemeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentsActivity extends AppCompatActivity {
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        backButton = findViewById(R.id.back_button);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userRole = sharedPreferences.getString("userRole", "");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (userRole.equals("Driver")) {
                    intent = new Intent(PaymentsActivity.this, RequestsActivity.class);
                } else {
                    intent = new Intent(PaymentsActivity.this, HomeActivity.class);
                }
                startActivity(intent);
            }
        });
    }
}
