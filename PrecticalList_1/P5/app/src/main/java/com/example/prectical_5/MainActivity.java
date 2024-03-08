package com.example.prectical_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText kilogramEditText;
    private Button convertButton;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kilogramEditText = findViewById(R.id.kilogramEditText);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertKilogramToPounds();
            }
        });
    }
    private void convertKilogramToPounds() {
        String kilogramStr = kilogramEditText.getText().toString().trim();

        if (!kilogramStr.isEmpty()) {
            try {
                double kilograms = Double.parseDouble(kilogramStr);
                double pounds = kilograms * 2.20462;
                resultTextView.setText(String.format("%.2f Kilograms is %.2f Pounds", kilograms, pounds));
            } catch (NumberFormatException e) {
                resultTextView.setText("Invalid input. Please enter a valid number.");
            }
        } else {
            resultTextView.setText("Please enter a weight in kilograms.");
        }
    }
}