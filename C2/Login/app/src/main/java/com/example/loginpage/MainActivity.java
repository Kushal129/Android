package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email , password;
    private Button btn_login;

    private  DBhelper dBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        btn_login = findViewById(R.id.btn_login);
        dBhelper = new DBhelper(this);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email  = email.getText().toString().trim();
                String pass = password.getText().toString();

                if (validateInput(email, password)) {
                    loginUser(email, password);
                }
            }
        });
    }

    private void loginUser(EditText email, EditText password) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();

        String[] colums = {dBhelper.eid};
        String selection = dBhelper.ename + " = ? " +
                " AND " + dBhelper.pass + " = ? ";

        String[] selectionArgs = {email , password};
        Cursor cursor = db.query(dBhelper.tblname ,colums ,selection ,selectionArgs ,null , null , null);
        if (cursor.getCount() > 0) {
            // Login successful, redirect to welcome page
            Intent welcomeIntent = new Intent(MainActivity.this,welcomeactivity.class);
            startActivity(welcomeIntent);
        } else {
            // Login failed, display error message
            Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    private boolean validateInput(EditText email, EditText password) {
        return true;
    }
}