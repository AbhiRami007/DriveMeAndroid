package com.example.drivemeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private TextView cancelButton;
    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cancelButton = findViewById(R.id.cancel_button);
        loginButton = findViewById(R.id.login_button);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Dummy check for login credentials
                String role = checkCredentials(email, password);

                if (role != null) {
                    navigateToNextActivity(role);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String checkCredentials(String email, String password) {
        // Dummy data
        if (email.equals("driver@example.com") && password.equals("password")) {
            return "Driver";
        } else if (email.equals("passenger@example.com") && password.equals("password")) {
            return "Passenger";
        }
        return null;
    }

    private void navigateToNextActivity(String role) {
        Intent intent;
        if (role.equals("Driver")) {
            intent = new Intent(LoginActivity.this, RequestsActivity.class);
        } else {
            intent = new Intent(LoginActivity.this, HomeActivity.class);
        }
        startActivity(intent);
        finish(); // Optional: Close the LoginActivity so the user can't return to it
    }
}
