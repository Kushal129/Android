package com.example.reguser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        dbHelper = new DBHelper(this);
    }

    public void onLoginClick(View view) {
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        TextView errorTextView = findViewById(R.id.errorTextView);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            errorTextView.setText("Please enter both username and password");
            errorTextView.setVisibility(View.VISIBLE);
            return;
        }

        if (username.equals("admin") && password.equals("admin123")) {
            startActivity(new Intent(this, AdminActivity.class));
            return;
        }


        Cursor cursor = dbHelper.getUserByUsernamePassword(username, password);

        if (cursor.moveToFirst()) {

            startActivity(new Intent(this, UserActivity.class));
        } else {

            errorTextView.setText("Incorrect username or password");
            errorTextView.setVisibility(View.VISIBLE);
        }

        cursor.close();
    }
}
