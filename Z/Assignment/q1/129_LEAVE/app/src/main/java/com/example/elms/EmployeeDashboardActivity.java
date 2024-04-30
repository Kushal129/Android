package com.example.elms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class EmployeeDashboardActivity extends AppCompatActivity {
    private EditText leaveDateEditText, leaveReasonEditText;
    private TextView leaveStatusTextView;
    private Button applyLeaveButton, viewLeavesButton, logoutButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);

        leaveDateEditText = findViewById(R.id.leaveDateEditText);
        leaveReasonEditText = findViewById(R.id.leaveReasonEditText);
        leaveStatusTextView = findViewById(R.id.leaveStatusTextView);
        applyLeaveButton = findViewById(R.id.applyLeaveButton);
        viewLeavesButton = findViewById(R.id.viewLeavesButton);
        logoutButton = findViewById(R.id.logoutButton);

        databaseHelper = new DatabaseHelper(this);

        leaveDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        applyLeaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyLeave();
            }
        });

        viewLeavesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLeaves();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(EmployeeDashboardActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                        leaveDateEditText.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

@SuppressLint("SetTextI18n")
    private void applyLeave() {
        String leaveDate = leaveDateEditText.getText().toString().trim();
        String leaveReason = leaveReasonEditText.getText().toString().trim();

        if (leaveDate.isEmpty() || leaveReason.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = databaseHelper.addLeaveRequest(leaveDate, leaveReason, "Pending");

        if (result != -1) {
            leaveStatusTextView.setText("Leave Applied: " + leaveDate);
            leaveStatusTextView.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Failed to apply leave", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewLeaves() {
        List<LeaveRequest> leaveRequests = databaseHelper.getAllLeaveRequests();

        if (leaveRequests.isEmpty()) {
            leaveStatusTextView.setText("No leave requests found");
        } else {
            StringBuilder leaveRequestsInfo = new StringBuilder();


            for (LeaveRequest request : leaveRequests) {
                leaveRequestsInfo.append("Date: ").append(request.getDate()).append("\n")
                        .append("Reason: ").append(request.getReason()).append("\n")
                        .append("Status: ").append(request.getStatus()).append("\n\n");
            }

            leaveStatusTextView.setText(leaveRequestsInfo.toString());
        }
    }

    private void logout() {
        Intent i = new Intent(EmployeeDashboardActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}