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





        // Initialize UI elements
        leaveDateEditText = findViewById(R.id.leaveDateEditText);
        leaveReasonEditText = findViewById(R.id.leaveReasonEditText);
        leaveStatusTextView = findViewById(R.id.leaveStatusTextView);
        applyLeaveButton = findViewById(R.id.applyLeaveButton);
        viewLeavesButton = findViewById(R.id.viewLeavesButton);
        logoutButton = findViewById(R.id.logoutButton);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set listener for leave date picker
        leaveDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Set listener for apply leave button
        applyLeaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyLeave();
            }
        });

        // Set listener for view leaves button
        viewLeavesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLeaves();
            }
        });

        // Set listener for logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(); // Call the logout method when logoutButton is clicked
            }
        });

        // Initially, hide leave status display
        // leaveStatusTextView.setVisibility(View.GONE);
    }

    // Method to display date picker dialog
    private void showDatePicker() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(EmployeeDashboardActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update leave date EditText with selected date
                        String selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                        leaveDateEditText.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        // Show date picker dialog
        datePickerDialog.show();
    }

    // Method to handle leave application
    @SuppressLint("SetTextI18n")
    private void applyLeave() {
        // Retrieve input values
        String leaveDate = leaveDateEditText.getText().toString().trim();
        String leaveReason = leaveReasonEditText.getText().toString().trim();

        // Validate input fields
        if (leaveDate.isEmpty() || leaveReason.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add leave request to database
        long result = databaseHelper.addLeaveRequest(leaveDate, leaveReason, "Pending");

        // Check if leave request was added successfully
        if (result != -1) {
            // Display leave status
            leaveStatusTextView.setText("Leave Applied: " + leaveDate);
            leaveStatusTextView.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Failed to apply leave", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to view applied leaves
    private void viewLeaves() {
        // Retrieve all leave requests from the database
        List<LeaveRequest> leaveRequests = databaseHelper.getAllLeaveRequests();

        // Check if there are any leave requests
        if (leaveRequests.isEmpty()) {
            // No leave requests found
//            Toast.makeText(this, "No leave requests found", Toast.LENGTH_SHORT).show();
            leaveStatusTextView.setText("No leave requests found");
        } else {
            // Create a string to store leave requests information
            StringBuilder leaveRequestsInfo = new StringBuilder();

            // Iterate over the list of leave requests
            for (LeaveRequest request : leaveRequests) {
                // Append leave request details to the string
                leaveRequestsInfo.append("Date: ").append(request.getDate()).append("\n")
                        .append("Reason: ").append(request.getReason()).append("\n")
                        .append("Status: ").append(request.getStatus()).append("\n\n");
            }

            // Display leave requests in a toast message
            // Toast.makeText(this, leaveRequestsInfo.toString(), Toast.LENGTH_LONG).show();
            leaveStatusTextView.setText(leaveRequestsInfo.toString());
        }
    }


    // Method to handle logout
    private void logout() {
        // Clear session or authentication state here
        // For example:
        // SessionManager.clearSession(); // Clear session data

        // Navigate back to the login activity
        Intent intent = new Intent(EmployeeDashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to dashboard on back press
    }


}