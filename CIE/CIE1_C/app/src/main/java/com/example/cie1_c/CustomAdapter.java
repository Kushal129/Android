package com.example.cie1_c;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {
    private static Activity Context;
    private static String[] ProductName;
    private static String[] Description;
    private static int[] ProductImages;

    public CustomAdapter(@NonNull Activity context,String[] productname, String[] description,
                         int[] images) {
        super(context, R.layout.custom_item_list,productname);
        this.Context = context;
        this.ProductName = productname;
        this.Description = description;
        this.ProductImages = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_list,parent,false);

        TextView lblProductName = view.findViewById(R.id.lblProductName);
        TextView lblDescription = view.findViewById(R.id.lblProductDescription);
        ImageView imgProduct = view.findViewById(R.id.imgProduct);

        lblProductName.setText(ProductName[position].toString());
        lblDescription.setText(Description[position].toString());
        imgProduct.setImageResource(ProductImages[position]);
        return view;
    }
}
