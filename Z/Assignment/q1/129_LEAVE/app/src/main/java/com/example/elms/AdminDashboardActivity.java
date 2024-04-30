package com.example.elms;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {
    private ListView leaveRequestsListView;
    Button logout;
    private LeaveRequestAdapter adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        leaveRequestsListView = findViewById(R.id.leaveRequestsListView);
        databaseHelper = new DatabaseHelper(this);

        List<LeaveRequest> leaveRequests = databaseHelper.getAllLeaveRequests();

        adapter = new LeaveRequestAdapter(this, leaveRequests);
        leaveRequestsListView.setAdapter(adapter);
        logout = findViewById (R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    private void logout() {
        Intent i = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}