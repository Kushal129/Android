package com.example.cie1_c;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Registration extends Fragment {


    public Registration() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        TextView lblContent = view.findViewById(R.id.lblContent);
        TextView lblContactNo = view.findViewById(R.id.lblContactNo);
        Button btnSMS = view.findViewById(R.id.btnSMS);
        Bundle bundle = getArguments();
        if(bundle != null) {
            String content = bundle.getString("content");
            String contactNo = bundle.getString("contactno");
            lblContent.setText(content);
            lblContactNo.setText("Contact No : " + contactNo);
            btnSMS.setVisibility(View.VISIBLE);
            btnSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_SENDTO);
                    i.putExtra("sms_body",content);
                    i.putExtra("address",contactNo);
                    i.setData(Uri.parse("smsto:"));
                    startActivity(i);
                }
            });
        }
        return view;
    }
}