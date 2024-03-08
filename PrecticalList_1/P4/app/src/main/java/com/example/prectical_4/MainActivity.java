package com.example.prectical_4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numberEditText = findViewById(R.id.numberEditText);
        Button checkPalindromeButton = findViewById(R.id.checkPalindromeButton);

        checkPalindromeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberStr = numberEditText.getText().toString().trim();

                if (!numberStr.isEmpty()) {
                    int number = Integer.parseInt(numberStr);
                    String message = isPalindrome(number) ?
                            "The number is a palindrome!" :
                            "The number is not a palindrome.";

                    showToast(message);
                } else {
                    showToast("Please enter a number.");
                }
            }
        });
    }
    private boolean isPalindrome(int number) {
        int reversedNumber = 0;
        int tempNumber = number;

        while (tempNumber > 0) {
            reversedNumber = reversedNumber * 10 + tempNumber % 10;
            tempNumber /= 10;
        }
        return number == reversedNumber;
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}