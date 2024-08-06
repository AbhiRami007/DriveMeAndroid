package com.example.drivemeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drivemeandroid.models.UserDetails;

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

                new CheckCredentialsTask().execute(email, password);
            }
        });
    }

    private class CheckCredentialsTask extends AsyncTask<String, Void, UserDetails> {
        @Override
        protected UserDetails doInBackground(String... strings) {
            String email = strings[0];
            String password = strings[1];
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            return db.userDao().getUserByEmailAndPassword(email, password);
        }

        @Override
        protected void onPostExecute(UserDetails userDetails) {
            Log.i("TAG", "onPostExecute: "+passwordEditText.getText().toString() );
           if (userDetails.getEmail().equals(emailEditText.getText().toString())
                    && userDetails.getPassword().equals(passwordEditText.getText().toString()) ) {
                navigateToNextActivity(userDetails);
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToNextActivity(UserDetails userDetails) {
        Intent intent;
        if (userDetails.getUserRole().equals("Driver")) {
            intent = new Intent(LoginActivity.this, RequestsActivity.class);
        } else {
            intent = new Intent(LoginActivity.this, HomeActivity.class);
        }
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userDetails.getUserId());
        editor.putString("email", userDetails.getEmail());
        editor.putString("name", userDetails.getName());
        editor.putString("userRole", userDetails.getUserRole());
        editor.apply(); // or editor.commit();

        startActivity(intent);
        finish();
    }
}
