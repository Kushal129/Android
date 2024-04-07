package com.example.elms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {
    private ListView leaveRequestsListView;
    private LeaveRequestAdapter adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        leaveRequestsListView = findViewById(R.id.leaveRequestsListView);
        databaseHelper = new DatabaseHelper(this);

        // Retrieve leave requests from the database
        List<LeaveRequest> leaveRequests = databaseHelper.getAllLeaveRequests();

        // Initialize and set adapter for the list view
        adapter = new LeaveRequestAdapter(this, leaveRequests);
        leaveRequestsListView.setAdapter(adapter);
    }
}