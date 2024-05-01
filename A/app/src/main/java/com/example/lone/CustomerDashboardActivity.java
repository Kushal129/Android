package com.example.lone;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerDashboardActivity extends AppCompatActivity {

    private EditText editTextLoanType, editTextLoanAmount, editTextLoanTenure,
            editTextEmail, editTextName, editTextContactNo;
    private Button buttonApplyLoan;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        editTextLoanType = findViewById(R.id.editTextLoanType);
        editTextLoanAmount = findViewById(R.id.editTextLoanAmount);
        editTextLoanTenure = findViewById(R.id.editTextLoanTenure);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextName = findViewById(R.id.editTextName);
        editTextContactNo = findViewById(R.id.editTextContactNo);
        buttonApplyLoan = findViewById(R.id.buttonApplyLoan);

        buttonApplyLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyLoan();
            }
        });
    }

    private void applyLoan() {
        String loanType = editTextLoanType.getText().toString();
        String loanAmountStr = editTextLoanAmount.getText().toString();
        String loanTenureStr = editTextLoanTenure.getText().toString();
        String email = editTextEmail.getText().toString();
        String name = editTextName.getText().toString();
        String contactNo = editTextContactNo.getText().toString();

        // Validate input fields
        if (loanType.isEmpty() || loanAmountStr.isEmpty() || loanTenureStr.isEmpty() ||
                email.isEmpty() || name.isEmpty() || contactNo.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double loanAmount = Double.parseDouble(loanAmountStr);
        int loanTenure = Integer.parseInt(loanTenureStr);

        // Calculate loan EMI
        double interestRate = getInterestRate(loanType);
        double monthlyInterestRate = interestRate / 100 / 12;
        double emi = calculateEMI(loanAmount, monthlyInterestRate, loanTenure);

        // Display confirmation dialog
        showConfirmationDialog(emi);
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
                // Display Toast message
                Toast.makeText(CustomerDashboardActivity.this, "Your loan is applied successfully", Toast.LENGTH_SHORT).show();

                // Send notification to customer (Implement notification logic here)

                // Clear input fields if needed
                clearInputFields();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing or show cancellation message
                Toast.makeText(CustomerDashboardActivity.this, "Loan application cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clearInputFields() {
        editTextLoanType.setText("");
        editTextLoanAmount.setText("");
        editTextLoanTenure.setText("");
        editTextEmail.setText("");
        editTextName.setText("");
        editTextContactNo.setText("");
    }
}
