package com.example.drivemeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay for splash screen
        new Handler().postDelayed(() -> {
            // Start main activity after delay
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 2000); // 2 seconds delay
    }
}

