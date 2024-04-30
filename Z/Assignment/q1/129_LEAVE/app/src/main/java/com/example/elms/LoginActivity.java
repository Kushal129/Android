package com.example.elms;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        databaseHelper = new DatabaseHelper(this);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    
    public void openRegistrationActivity(View view) {
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
    }

    private void login() {
        String username = usernameEditText.getText ().toString ().trim ();
        String password = passwordEditText.getText ().toString ().trim ();

        User user = databaseHelper.getUser (username, password);

        if (user != null && user.getPassword ().equals (password)) {
            SharedPreferences sharedPreferences = getSharedPreferences ("loginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit ();
            editor.putBoolean ("loggedIn", true);
            editor.putString ("userType", user.getUserType ());
            editor.apply ();
            if (user.getUserType ().equals ("admin")) {
                startActivity (new Intent (LoginActivity.this, AdminDashboardActivity.class));
            } else if (user.getUserType ().equals ("employee")) {
                Intent intent = new Intent (LoginActivity.this, EmployeeDashboardActivity.class);
                intent.putExtra ("userId", user.getId ());
                startActivity (intent);
            }
            finish ();
        } else {
            Toast.makeText (this, "Invalid username or password", Toast.LENGTH_SHORT).show ();
        }
    }

}




