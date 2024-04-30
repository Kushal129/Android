package com.example.reguser;

import static com.example.reguser.DBHelper.COLUMN_USERNAME;
import static com.example.reguser.DBHelper.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        dbHelper = new DBHelper(this);

        ListView userListView = findViewById(R.id.userListView);

        List<String> purchases = getAllPurchases();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, purchases);
        userListView.setAdapter(adapter);
    }

    private List<String> getAllPurchases() {
        List<String> purchases = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_BUY, new String[]{DBHelper.COLUMN_USERNAME, DBHelper.COLUMN_PRODUCT_NAME}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_USERNAME));
            String productName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PRODUCT_NAME));
            purchases.add("User: " + username + ", Product: " + productName);
        }

        cursor.close();
        db.close();
        return purchases;
    }
}