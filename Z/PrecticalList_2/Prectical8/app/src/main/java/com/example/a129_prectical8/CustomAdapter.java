package com.example.a129_prectical8;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int[] Images;
    public CustomAdapter(Context context, int[] Images) {
        this.context = context;
        this.Images = Images;
    }
    @Override
    public int getCount() {
        return Images.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new
                    ViewGroup.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(Images[position]);
        return imageView;
    }
}




