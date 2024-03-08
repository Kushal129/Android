package com.example.optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        if(itemID == R.id.menuProfile){
            Toast.makeText(this, "Click Profile", Toast.LENGTH_SHORT).show();
        }
        if(itemID == R.id.menuSetting){
            Toast.makeText(this, "Click Setting", Toast.LENGTH_SHORT).show();
        }
        if(itemID == R.id.menuView){
            Toast.makeText(this, "Click View", Toast.LENGTH_SHORT).show();
        }
        if(itemID == R.id.menuAbout){
            Toast.makeText(this, "Click About", Toast.LENGTH_SHORT).show();
        }
        if(itemID == R.id.menuLogout){
            Toast.makeText(this, "Click Logout", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.options_menu , menu);

        return true;
    }
}