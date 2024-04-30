package com.example.a129_prectical6;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ListView colorListView;
    LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        colorListView = findViewById(R.id.colorListView);
        mainLayout = findViewById(R.id.main);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.color_names,android.R.layout.simple_list_item_1);
        colorListView.setAdapter(adapter);
        colorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedColor = (String) parent.getItemAtPosition(position);
                changeBackgroundColor(selectedColor);
            }
        });
    }
    protected void changeBackgroundColor(String colorName) {
        int colorValue;
        switch (colorName.toLowerCase()) {
            case "red":
                colorValue = Color.RED;
                break;
            case "green":
                colorValue = Color.GREEN;
                break;
            case "pink":
                colorValue = Color.LTGRAY;
                break;
            case "blue":
                colorValue = Color.BLUE;
                break;
            case "yellow":
                colorValue = Color.YELLOW;
                break;
            default:
                colorValue = Color.WHITE;
                break;
        }
        mainLayout.setBackgroundColor(colorValue);
    }
}
