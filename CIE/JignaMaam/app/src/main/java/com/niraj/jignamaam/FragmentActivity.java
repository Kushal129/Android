package com.niraj.jignamaam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity {


    Button btnRegistration, btnList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ControlInitialization();
        ButtonClicks();
    }

    private void ControlInitialization()
    {
        btnRegistration = findViewById(R.id.btnRegister);
        btnList = findViewById(R.id.btnList);
    }

    private void ButtonClicks()
    {
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadFragment(new RegisterFragment());
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadFragment(new ContactListFragment());
            }
        });
    }

    /*private void test(View v)
    {
    }*/
    private void LoadFragment(Fragment f)
    {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction t = fm.beginTransaction();
        t.replace(R.id.frame_container,f);
        t.addToBackStack(null);
        t.commit();
    }
}