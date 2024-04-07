package com.example.elms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText, userTypeEditText;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize EditText fields
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        userTypeEditText = findViewById(R.id.userTypeEditText);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(v); // Call the register method when the button is clicked
            }
        });
    }

    // Method triggered when the registration button is clicked
    public void register(View view) {
        // Retrieve username, password, and user type from EditText fields
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String userType = userTypeEditText.getText().toString().trim();

        // Validate input (you can add more validation here)
        if (username.isEmpty() || password.isEmpty() || userType.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new User object
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);

        // Add the user to the database
        long result = databaseHelper.addUser(user);

        // Check if user registration was successful
        if (result != -1) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            // Redirect to login activity
            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show();
        }
    }
}