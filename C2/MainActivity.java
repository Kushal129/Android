package com.example.cie2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {
    DBhelper helper;
    DBmanage db;
    Button btn_login;
    EditText txt_email , txt_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        addbtnclick();
        GetEmp();
    }

    private void addbtnclick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),addempdetails.class);
                startActivity(i);
            }
        });
    }


    private void initialize() {
        btn_login = findViewById(R.id.addemp);
        txt_email = findViewById( R.id.txt_email);
        txt_pass = findViewById(R.id.txt_pass);
    }
    private void GetEmp(){
        Cursor c = db.GetEmplist();
        String[] from = {helper.eid,helper.ename,helper.pass};
    }

}