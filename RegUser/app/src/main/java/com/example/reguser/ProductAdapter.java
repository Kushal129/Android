package com.example.reguser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product>  {
    private Context mContext;
    private int mResource;

    public ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        mContext = context;
        mResource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product product = getItem(position);


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }


        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);
        ImageView ivProductImage = convertView.findViewById(R.id.ivProductImage);

        tvName.setText(product.getName());
        tvDescription.setText(product.getDescription());
        ivProductImage.setImageResource(product.getImageResource());

        return convertView;
    }

}
