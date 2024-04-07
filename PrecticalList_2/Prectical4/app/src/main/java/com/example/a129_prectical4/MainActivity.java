package com.example.a129_prectical4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgCourse;
    Button btnSubmit;
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

        rgCourse = findViewById(R.id.rgCourse);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             int selectedId = rgCourse.getCheckedRadioButtonId();
                                             if (selectedId != -1) {
                                                 RadioButton selectedRadioButton =
                                                         findViewById(selectedId);
                                                 String selectedCourse =
                                                         selectedRadioButton.getText().toString();
                                                 Toast.makeText(MainActivity.this, "Selected Course: " +
                                                         selectedCourse, Toast.LENGTH_SHORT).show();


                                             } else {
                                                 Toast.makeText(MainActivity.this, "Please select a course",
                                                         Toast.LENGTH_SHORT).show();
                                             }
                                         }
                                     }
        )
        ;

    }
}