package com.example.drivemeandroid;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drivemeandroid.models.UserDetails;

public class SignUpActivity extends AppCompatActivity {
    private TextView cancelButton;
    private Button signUpButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText nameEditText, rate;
    private String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        cancelButton = findViewById(R.id.cancelButton);
        signUpButton = findViewById(R.id.signupButton);
        emailEditText = findViewById(R.id.inputEmail);
        passwordEditText = findViewById(R.id.inputPassword);
        nameEditText = findViewById(R.id.inputName);
        rate = findViewById(R.id.inputRate);
        userRole = getIntent().getStringExtra("userRole");
        if(userRole.equals("Driver")){
            rate.setVisibility(View.VISIBLE);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String name = nameEditText.getText().toString();
                int charge = userRole.equals("Driver")?parseInt(String.valueOf(rate.getText())):0;
                if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    UserDetails userDetails = new UserDetails();
                    userDetails.setEmail(email);
                    userDetails.setPassword(password);
                    userDetails.setName(name);
                    userDetails.setUserRole(userRole);
                    userDetails.setChargePerHour(charge);
                    new InsertUserTask().execute(userDetails);
                }
            }
        });
    }

    private class InsertUserTask extends AsyncTask<UserDetails, Void, Void> {
        @Override
        protected Void doInBackground(UserDetails... userDetails) {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.userDao().insertUser(userDetails[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(SignUpActivity.this, "Sign-up successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpActivity.this, SuccessActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
