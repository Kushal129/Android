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
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("loggedIn", false)) {
            String userType = sharedPreferences.getString("userType", "");
            if (userType.equals("admin")) {
                startActivity(new Intent(MainActivity.this, AdminDashboardActivity.class));
            } else if (userType.equals("employee")) {
                startActivity(new Intent(MainActivity.this, EmployeeDashboardActivity.class));
            }
            finish();
        }

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












