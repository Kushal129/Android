package com.example.datainalertbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class MainActivity extends AppCompatActivity {
    EditText txtName;
    Spinner spCity;
    RadioGroup rgGender;
    RadioButton rbMale,rbFemale;
    CheckBox ckSing,ckDance,ckShare;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlInitialization();
        ButtonClicks();
    }

    private void ButtonClicks(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = txtName.getText().toString();
                String Gender = "";
                if (rbMale.isChecked()) {
                    Gender = rbMale.getText().toString();
                } else
                    Gender = rbFemale.getText().toString();

                String City = spCity.getSelectedItem().toString();
                String hobby = "";
                if (ckDance.isChecked()) {
                    hobby = ckDance.getText().toString();
                }
                if (ckShare.isChecked()) {
                    hobby = ckShare.getText().toString();
                }
                if (ckShare.isChecked()) {
                    hobby = ckShare.getText().toString();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert Dialog Example");
                builder.setMessage("Name : "+Name +"\n"+
                        "City : "+ City+"\n"+
                        "Gender :" + Gender +"\n"+
                        "Hobby : " + hobby +"\n"
                );
                builder.setCancelable(false);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private void ControlInitialization(){
        txtName=findViewById(R.id.txtName);
        spCity=findViewById(R.id.spCity);
        rgGender=findViewById(R.id.rgGender);
        rbMale=findViewById(R.id.rbMale);
        rbFemale=findViewById(R.id.rbFemale);
        ckDance=findViewById(R.id.ckDance);
        ckSing=findViewById(R.id.ckSing);
        ckShare=findViewById(R.id.ckShare);
        btnRegister=findViewById(R.id.btnRegister);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spCity));
        spCity.setAdapter(adapter);
    }
}