package com.example.a129_prectical13;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextNo1, editTextNo2;
    TextView resultTextView;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        EdgeToEdge.enable (this);
        setContentView (R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener (findViewById (R.id.main), ( v, insets ) -> {
            Insets systemBars = insets.getInsets (WindowInsetsCompat.Type.systemBars ());
            v.setPadding (systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNo1 = findViewById(R.id.editTextNo1);
        editTextNo2 = findViewById(R.id.editTextNo2);
        resultTextView = findViewById(R.id.resultTextView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        String strNo1 = editTextNo1.getText().toString();
        String strNo2 = editTextNo2.getText().toString();

        double no1 = Double.parseDouble(strNo1);
        double no2 = Double.parseDouble(strNo2);
        double result = 0;
        if (id == R.id.menuAddition) {
            result = no1 + no2;
        } else if (id == R.id.menuSubtraction) {
            result = no1 - no2;
        } else if (id == R.id.menuMultiplication) {
            result = no1 * no2;
        }
        resultTextView.setText(String.valueOf(result));
        return super.onOptionsItemSelected(item);
    }
}