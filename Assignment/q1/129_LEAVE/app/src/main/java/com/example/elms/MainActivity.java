package com.example.elms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        // Check if user is already logged inadmin
        if (sharedPreferences.getBoolean("loggedIn", false)) {
            // Retrieve user type
            String userType = sharedPreferences.getString("userType", "");
            // Redirect to respective dashboard activity
            if (userType.equals("admin")) {
                startActivity(new Intent(MainActivity.this, AdminDashboardActivity.class));
            } else if (userType.equals("employee")) {
                startActivity(new Intent(MainActivity.this, EmployeeDashboardActivity.class));
            }
            // Finish MainActivity
            finish();
        }

        // Setup login button click listener
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}












