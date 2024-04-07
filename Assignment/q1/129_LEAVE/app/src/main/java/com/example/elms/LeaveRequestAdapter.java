package com.example.elms;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class LeaveRequestAdapter extends ArrayAdapter<LeaveRequest> {

    private Context context;
    private List<LeaveRequest> leaveRequests;
    private DatabaseHelper databaseHelper;

    public LeaveRequestAdapter(Context context, List<LeaveRequest> leaveRequests) {
        super(context, R.layout.leave_request_item, leaveRequests);
        this.context = context;
        this.leaveRequests = leaveRequests;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.leave_request_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.dateTextView = convertView.findViewById(R.id.leaveDateTextView);
            viewHolder.reasonTextView = convertView.findViewById(R.id.leaveReasonTextView);
            viewHolder.statusTextView = convertView.findViewById(R.id.leaveStatusTextView);
            viewHolder.acceptButton = convertView.findViewById(R.id.acceptButton);
            viewHolder.rejectButton = convertView.findViewById(R.id.rejectButton);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final LeaveRequest leaveRequest = leaveRequests.get(position);

        // Bind leave request data to views
        viewHolder.dateTextView.setText(leaveRequest.getDate());
        viewHolder.reasonTextView.setText(leaveRequest.getReason());
        viewHolder.statusTextView.setText(leaveRequest.getStatus());

        // Handle accept button click
        viewHolder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update leave request status to "Accepted" in the database
                databaseHelper.updateLeaveRequestStatus(leaveRequest.getId(), "Accepted");
                // Update status text view
                leaveRequest.setStatus("Accepted");
                notifyDataSetChanged();
            }
        });

        // Handle reject button click
        viewHolder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update leave request status to "Rejected" in the database
                databaseHelper.updateLeaveRequestStatus(leaveRequest.getId(), "Rejected");
                // Update status text view
                leaveRequest.setStatus("Rejected");
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView dateTextView;
        TextView reasonTextView;
        TextView statusTextView;
        Button acceptButton;
        Button rejectButton;
    }
}
