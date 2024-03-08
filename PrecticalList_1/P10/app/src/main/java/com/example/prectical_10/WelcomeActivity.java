package com.example.prectical_10;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity\_welcome);

        TextView welcomeTextView = findViewById(R.id.welcome);
        welcomeTextView.setText("Welcome admin");
    }
}
