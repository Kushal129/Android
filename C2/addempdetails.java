package com.example.cie2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addempdetails extends AppCompatActivity {
// S-1
    DBhelper helper;
    DBmanage db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addempdetails);
        //S-2
        helper = new DBhelper(this);
        db = new DBmanage(this);
        db.open(); // jaruri che nhi chale databse

        EditText uname = findViewById(R.id.txt_email);
        EditText pass = findViewById(R.id.txt_pass);
        Button  add = findViewById(R.id.add);


        //S-3
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString();
                String password = pass.getText().toString();

                // on click per insert karavnu have data lai lidho upar
                long in = db.AddEmp(username , password);
                if (in > 0 ){
                    Toast.makeText(getApplicationContext(), "Thai gaiiii", Toast.LENGTH_LONG).show();
                    BacktoHome();

                }else {
                    //update mate ac.main ma listview banavu
                }
                // Altu kari ne add thase details
            }

        });
    }
    private void BacktoHome() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}