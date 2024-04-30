package com.niraj.jignamaam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailFragment extends Fragment {

    TextView lblCourse, lblDescription;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        lblCourse = view.findViewById(R.id.lblCourse);
        lblDescription = view.findViewById(R.id.lblDescription);
        return view;
    }

    public void ChangeData(String Course, String Description)
    {
        lblCourse.setText(Course);
        lblDescription.setText(Description);
    }
}