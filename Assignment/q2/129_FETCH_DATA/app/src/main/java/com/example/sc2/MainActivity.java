package com.example.sc2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String URL = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    ListView lstTennis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstTennis = findViewById(R.id.lstTennis);
        RequestJSON();
    }

    private void RequestJSON() {
        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean status = jsonResponse.getBoolean("status");
                            if (status) {
                                JSONArray jsonArray = jsonResponse.getJSONArray("data");
                                ArrayList<String> dataList = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject dataObject = jsonArray.getJSONObject(i);
                                    String id = dataObject.getString("id");
                                    String name = dataObject.getString("name");
                                    String country = dataObject.getString("country");
                                    String imgURL = dataObject.getString("imgURL");

                                    // Create a formatted string including all fields
                                    String item = "ID: " + id + "\nName: " + name + "\nCountry: " + country + "\nImage URL: " + imgURL;

                                    dataList.add(item);
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                                        android.R.layout.simple_list_item_1, dataList);
                                lstTennis.setAdapter(adapter);
                            } else {
                                String message = jsonResponse.getString("message");
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Failed to parse JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
