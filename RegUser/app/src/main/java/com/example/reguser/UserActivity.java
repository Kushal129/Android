package com.example.reguser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ListView productListView;
    ArrayList<Product> productList;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        productListView = findViewById(R.id.productListView);
        productList = new ArrayList<> ();


        productList.add(new Product("ABC", "Nice Product", R.drawable.ic_launcher_background));
        productList.add(new Product("XYZ", "Good Product", R.drawable.ic_launcher_background));
        productList.add(new Product("KHK", "Best Product", R.drawable.ic_launcher_background));


        productAdapter = new ProductAdapter(this, R.layout.product_item, productList);
        productListView.setAdapter(productAdapter);

        productListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( AdapterView<?> parent, View view, int position, long id) {

                Product product = productList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setTitle("Buy Product")
                        .setMessage("Do you want to buy " + product.getName() + "?")
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper dbHelper = new DBHelper(UserActivity.this);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                ContentValues values = new ContentValues ();
                                values.put(DBHelper.COLUMN_USERNAME, "current_username");
                                values.put(DBHelper.COLUMN_PRODUCT_NAME, product.getName());
                                db.insert(DBHelper.TABLE_BUY, null, values);
                                db.close();

                                Toast.makeText(UserActivity.this, "You bought " + product.getName(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();

                // Return true to consume the long click event
                return true;
            }
        });
    }

}
