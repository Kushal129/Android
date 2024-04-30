package com.niraj.jignamaam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {

    String[] Course = {"ewfewfef","erererefed","erytewyrtweuw"};
    String[] desc ={"wterweytwytdwyt","ergeyrgweyrgweyffgdhf","etrwtywtydshdhgsfgd"};
    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView lstCourse = view.findViewById(R.id.lblCourses);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,Course);
        lstCourse.setAdapter(adapter);

        lstCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailFragment obj = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailfragment);
                obj.ChangeData(Course[position],desc[position]);

                lstCourse.setSelector(android.R.color.holo_blue_light);
            }
        });
        return view;
    }
}