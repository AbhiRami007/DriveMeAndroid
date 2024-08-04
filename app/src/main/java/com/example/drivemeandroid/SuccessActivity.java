package com.example.drivemeandroid;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }

    public void checkEmail(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_EMAIL);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No email client available", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(SuccessActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
