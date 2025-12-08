package com.example.lab2bai5;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DSpinnerAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Dish> arrMonan;


    public DSpinnerAdapter(Context context, int layout, ArrayList<Dish> arrMonan) {
        this.context = context;
        this.layout = layout;
        this.arrMonan = arrMonan;
    }



    @Override
    public int getCount() {
        return arrMonan.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.dropdown_item, parent, false);

        TextView thbName = row.findViewById(R.id.thumbText);
        ImageView flag = row.findViewById(R.id.thumbImage);

        Drawable drawable = context.getResources().getDrawable(arrMonan.get(position).getThumbnail());

        thbName.setText(arrMonan.get(position).getName());
        flag.setImageDrawable(drawable);

        return row;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.dropdown_item, parent, false);

        TextView thbName = row.findViewById(R.id.thumbText);


        thbName.setText(arrMonan.get(position).getName());

        return row;
    }

}
