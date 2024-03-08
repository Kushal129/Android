package com.example.prectical_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText displayEditText;
    private StringBuilder inputStringBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayEditText = findViewById(R.id.displayEditText);
        inputStringBuilder = new StringBuilder();

        setButtonClickListener(R.id.btn0, "0");
        setButtonClickListener(R.id.btn1, "1");
        setButtonClickListener(R.id.btn2, "2");
        setButtonClickListener(R.id.btn3, "3");
        setButtonClickListener(R.id.btn4, "4");
        setButtonClickListener(R.id.btn5, "5");
        setButtonClickListener(R.id.btn6, "6");
        setButtonClickListener(R.id.btn7, "7");
        setButtonClickListener(R.id.btn8, "8");
        setButtonClickListener(R.id.btn9, "9");

        setButtonClickListener(R.id.btnAdd, "+");
        setButtonClickListener(R.id.btnSubtract, "-");
        setButtonClickListener(R.id.btnMultiply, "X");
        setButtonClickListener(R.id.btnDivide, "/");
        setButtonClickListener(R.id.btnEqual, "=");

        Button deleteButton = findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLastCharacter();
            }
        });
    }

    private void setButtonClickListener(int buttonId, final String buttonText) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonText.equals("=")) {
                    performCalculation();
                } else {
                    appendToDisplay(buttonText);
                }
            }
        });
    }

    private void appendToDisplay(String text) {
        inputStringBuilder.append(text);
        displayEditText.setText(inputStringBuilder.toString());
    }

    private void deleteLastCharacter() {
        if (inputStringBuilder.length() > 0) {
            inputStringBuilder.deleteCharAt(inputStringBuilder.length() - 1);
            displayEditText.setText(inputStringBuilder.toString());
        }
    }

    private void performCalculation() {
        if (inputStringBuilder.length() > 0) {
            try {
                String expression = inputStringBuilder.toString();
                double result = evaluateExpression(expression);

                displayEditText.setText(String.valueOf(result));

                // Clear the inputStringBuilder
                inputStringBuilder.setLength(0);

            } catch (Exception e) {
                displayEditText.setText("Error");
            }
        }
    }

    private double evaluateExpression(String expression) {
        try {
            return new java.util.Scanner(expression).useDelimiter("\\s*\\D\\s*").nextDouble();
        } catch (Exception e) {
            return 0;
        }
    }
}