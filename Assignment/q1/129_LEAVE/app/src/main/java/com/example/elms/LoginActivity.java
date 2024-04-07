package com.example.elms;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        databaseHelper = new DatabaseHelper(this);

        // Setup click listener for the login button
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });



    }


    public void openRegistrationActivity(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    // Method to handle login
    private void login() {
        // Retrieve username and password from EditText fields
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Call DatabaseHelper method to retrieve user information
        User user = databaseHelper.getUser(username, password);

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // Redirect user based on userType
            if (user.getUserType().equals("admin")) {
                startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class));
            } else if (user.getUserType().equals("employee")) {
                // Redirect to EmployeeDashboardActivity with user ID
                Intent intent = new Intent(LoginActivity.this, EmployeeDashboardActivity.class);
                intent.putExtra("userId", user.getId());
                startActivity(intent);
            }
            // Finish LoginActivity
            finish();
        } else {
            // Display error message for invalid credentials
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}




