package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email , pass;

    Button btn_regi;
    RadioButton male , female;
    Spinner role ;
    CheckBox vima;
    RadioGroup gender;
    DBhelper helper;
    DBmaneger db;

    String Semail , Spass ,Sgender , Srole , Svima ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBhelper(this);
        db = new DBmaneger(this);
        email = findViewById(R.id.email);
        btn_regi = findViewById(R.id.btn_regi);
        pass = findViewById(R.id.pass);
        gender = findViewById(R.id.redio_gender);
        role = findViewById(R.id.role);
        vima = findViewById(R.id.chackboxforvima);

       btnclick();
    }

    private void btnclick() {
        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Semail = email.getText().toString();
                Spass = pass.getText().toString();

                if(gender.getCheckedRadioButtonId() == R.id.male){
                    Sgender = "Male";
                } else if (gender.getCheckedRadioButtonId() == R.id.female) {
                    Sgender = "Female";
                }

                Srole = role.getSelectedItem().toString();

                if (vima.isChecked()){
                    Svima = "Yes";
                }
                else {
                    Svima = "No";
                }


                long in = db.add_emp(Spass,Semail,Sgender,Srole,Svima);
                if(in > 0){

                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);

                    Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "User Not Registered", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}