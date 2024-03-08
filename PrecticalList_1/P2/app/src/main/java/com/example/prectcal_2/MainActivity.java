package com.example.prectcal_2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextYear = findViewById(R.id.editTextYear);
        EditText editTextDays = findViewById(R.id.editTextDays);
        TextView textViewResult = findViewById(R.id.textViewResult);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String yearStr = editTextYear.getText().toString();
                String daysStr = editTextDays.getText().toString();

                if (!yearStr.isEmpty() && !daysStr.isEmpty()) {
                    int year = Integer.parseInt(yearStr);
                    int days = Integer.parseInt(daysStr);

                    if (days < 365) {
                        Date calculateDate = calculateDate(year, days);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                        String formattedDate = sdf.format(calculateDate);
                        textViewResult.setText("Result: " + formattedDate);
                    } else {
                        Toast.makeText(MainActivity.this, "Error: Number of days must be less than 365", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error: Please enter valid inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Date calculateDate(int year, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();


    }
}