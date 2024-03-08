package com.example.prectical_3;

import   androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextTemperature = findViewById(R.id.editTextTemperature);
        TextView textViewResult = findViewById(R.id.textViewResult);

        Button buttonConvertToF = findViewById(R.id.buttonConvertToF);
        buttonConvertToF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temperatureStr = editTextTemperature.getText().toString();

                if (!temperatureStr.isEmpty()) {
                    double temperatureCelsius = Double.parseDouble(temperatureStr);
                    double temperatureFahrenheit = celsiusToFahrenheit(temperatureCelsius);
                    textViewResult.setText("Result: " + temperatureFahrenheit + " °F");
                } else {
                    textViewResult.setText("Error: Please enter valid temperature");
                }
            }
        });

        Button buttonConvertToC = findViewById(R.id.buttonConvertToC);
        buttonConvertToC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temperatureStr = editTextTemperature.getText().toString();

                if (!temperatureStr.isEmpty()) {
                    double temperatureFahrenheit = Double.parseDouble(temperatureStr);
                    double temperatureCelsius = fahrenheitToCelsius(temperatureFahrenheit);
                    textViewResult.setText("Result: " + temperatureCelsius + " °C");
                } else {
                    textViewResult.setText("Error: Please enter valid temperature");
                }
            }
        });
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }
}