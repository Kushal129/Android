package com.example.a129_prectical5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    CheckBox iphoneCheckbox, androidCheckbox,
            windowsCheckbox;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iphoneCheckbox = findViewById(R.id.iphoneCheckbox);
        androidCheckbox = findViewById(R.id.androidCheckbox);
        windowsCheckbox = findViewById(R.id.windowsCheckbox);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        StringBuilder selectedOptions = new
                                                                StringBuilder("Selected Options: ");
                                                        if (iphoneCheckbox.isChecked()) {
                                                            selectedOptions.append("iPhone, ");
                                                        }
                                                        if (androidCheckbox.isChecked()) {
                                                            selectedOptions.append("Android, ");
                                                        }
                                                        if (windowsCheckbox.isChecked()) {
                                                            selectedOptions.append("Windows, ");
                                                        }
                                                        String result = selectedOptions.toString().trim();
                                                        if (result.endsWith(",")) {
                                                            result = result.substring(0, result.length() - 1);
                                                        }
                                                        Toast.makeText(MainActivity.this, result,
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                });
    }
}