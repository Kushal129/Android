package com.example.lone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lone.R;

import java.util.ArrayList;
import java.util.List;

public class AgentDashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerViewLoanApplications;
    private LoanApplicationsAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_dashboard);

        recyclerViewLoanApplications = findViewById(R.id.recyclerViewLoanApplications);
        recyclerViewLoanApplications.setLayoutManager(new LinearLayoutManager (this));
        adapter = new LoanApplicationsAdapter(getLoanApplications());
        recyclerViewLoanApplications.setAdapter(adapter);
    }

    private List<LoanApplication> getLoanApplicationsFromDatabase() {
        List<LoanApplication> applications = new ArrayList<>();
        // Assuming you have a Dbhelper class to handle database operations
        Dbhelper dbHelper = new Dbhelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("loan_applications", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String customerName = cursor.getString(cursor.getColumnIndex("customer_name"));
                String loanType = cursor.getString(cursor.getColumnIndex("loan_type"));
                double loanAmount = cursor.getDouble(cursor.getColumnIndex("loan_amount"));
                applications.add(new LoanApplication(customerName, loanType, loanAmount));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return applications;
    }
    private class LoanApplicationsAdapter extends RecyclerView.Adapter<LoanApplicationsAdapter.ViewHolder> {

        private List<LoanApplication> loanApplications;

        public LoanApplicationsAdapter(List<LoanApplication> loanApplications) {
            this.loanApplications = loanApplications;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loan_application, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            LoanApplication application = loanApplications.get(position);
            holder.textCustomerName.setText(application.getCustomerName());
            holder.textLoanType.setText(application.getLoanType());
            holder.textLoanAmount.setText(String.valueOf(application.getLoanAmount()));
            holder.buttonAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.buttonReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        @Override
        public int getItemCount() {
            return loanApplications.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textCustomerName, textLoanType, textLoanAmount;
            Button buttonAccept, buttonReject;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textCustomerName = itemView.findViewById(R.id.textCustomerName);
                textLoanType = itemView.findViewById(R.id.textLoanType);
                textLoanAmount = itemView.findViewById(R.id.textLoanAmount);
                buttonAccept = itemView.findViewById(R.id.buttonAccept);
                buttonReject = itemView.findViewById(R.id.buttonReject);
            }
        }
    }

    private class LoanApplication {
        private String customerName;
        private String loanType;
        private double loanAmount;

        public LoanApplication(String customerName, String loanType, double loanAmount) {
            this.customerName = customerName;
            this.loanType = loanType;
            this.loanAmount = loanAmount;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getLoanType() {
            return loanType;
        }

        public double getLoanAmount() {
            return loanAmount;
        }
    }
}
