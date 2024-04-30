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
    String JSON_URL = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    ListView tennisListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tennisListView = findViewById(R.id.lstTennis);
        fetchJSONData();
    }

    private void fetchJSONData() {
        StringRequest request = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean status = jsonResponse.getBoolean("status");
                            if (status) {
                                JSONArray jsonArray = jsonResponse.getJSONArray("data");
                                ArrayList<String> tennisDataList = new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject tennisObject = jsonArray.getJSONObject(i);
                                    String id = tennisObject.getString("id");
                                    String name = tennisObject.getString("name");
                                    String country = tennisObject.getString("country");
                                    String imgURL = tennisObject.getString("imgURL");

                                    String tennisItem = "ID: " + id + "\nName: " + name + "\nCountry: " + country + "\nImage URL: " + imgURL;
                                    tennisDataList.add(tennisItem);
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                                        android.R.layout.simple_list_item_1, tennisDataList);
                                tennisListView.setAdapter(adapter);
                            } else {
                                String message = jsonResponse.getString("message");
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error in to get url ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "Error to fetch data", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
