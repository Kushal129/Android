package com.example.lone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerDashboardActivity extends AppCompatActivity {

    private EditText  editTextLoanAmount, editTextLoanTenure,
            editTextEmail, editTextName, editTextContactNo;
    private Button buttonApplyLoan , buttonLogout;
    private ImageView imageViewLogo;

    private Spinner spinnerLoanType;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        spinnerLoanType = findViewById(R.id.spinnerLoanType);
        editTextLoanAmount = findViewById(R.id.editTextLoanAmount);
        editTextLoanTenure = findViewById(R.id.editTextLoanTenure);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextName = findViewById(R.id.editTextName);
        editTextContactNo = findViewById(R.id.editTextContactNo);
        buttonApplyLoan = findViewById(R.id.buttonApplyLoan);
        buttonLogout = findViewById(R.id.buttonLogout);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        startLogoAnimation();

        buttonApplyLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyLoan();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void startLogoAnimation() {
        RotateAnimation rs = new RotateAnimation (0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rs.setInterpolator(new LinearInterpolator ());
        rs.setDuration(2000);
        rs.setRepeatCount(Animation.INFINITE);
        imageViewLogo.startAnimation(rs);
    }
    private void applyLoan() {
        String loanType = spinnerLoanType.getSelectedItem().toString();
        String loanAmountStr = editTextLoanAmount.getText().toString();
        String loanTenureStr = editTextLoanTenure.getText().toString();
        String email = editTextEmail.getText().toString();
        String name = editTextName.getText().toString();
        String contactNo = editTextContactNo.getText().toString();

        if (loanType.isEmpty() || loanAmountStr.isEmpty() || loanTenureStr.isEmpty() ||
                email.isEmpty() || name.isEmpty() || contactNo.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double loanAmount = Double.parseDouble(loanAmountStr);
        int loanTenure = Integer.parseInt(loanTenureStr);

        double interestRate = getInterestRate(loanType);
        double monthlyInterestRate = interestRate / 100 / 12;
        double emi = calculateEMI(loanAmount, monthlyInterestRate, loanTenure);

        long id = storeLoanApplication(loanType, loanAmount, loanTenure, email, name, contactNo, emi);

        if (id != -1) {
            showConfirmationDialog(emi);
            clearInputFields();
        } else {
            Toast.makeText(this, "Error into apply loan.", Toast.LENGTH_SHORT).show();
        }
    }
    private long storeLoanApplication(String loanType, double loanAmount, int loanTenure, String email, String name, String contactNo, double emi) {
        Dbhelper dbHelper = new Dbhelper(this);
        return dbHelper.insertLoanApplication(loanType, loanAmount, loanTenure, email, name, contactNo, emi);
    }
    private double getInterestRate(String loanType) {
        switch (loanType) {
            case "Home Loan":
                return 9.15;
            case "Personal Loan":
                return 14;
            case "Education Loan":
                return 11.15;
            case "Car Loan":
                return 9.30;
            default:
                return 0;
        }
    }

    private double calculateEMI(double principal, double monthlyInterestRate, int tenureMonths) {
        double emi = principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths) /
                (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);
        return emi;
    }

    private void showConfirmationDialog(double emi) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Loan Application");
        builder.setMessage("Your loan EMI will be " + emi + " Rs. Per month. Are you sure you want to proceed?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomerDashboardActivity.this, "Your loan is applied successfully", Toast.LENGTH_SHORT).show();
                clearInputFields();
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CustomerDashboardActivity.this, "Loan application cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clearInputFields() {
        spinnerLoanType.setSelection(0);
        editTextLoanAmount.setText("");
        editTextLoanTenure.setText("");
        editTextEmail.setText("");
        editTextName.setText("");
        editTextContactNo.setText("");
    }

    private void logout() {
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
