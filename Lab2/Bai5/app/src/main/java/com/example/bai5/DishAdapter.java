package com.example.bai5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DishAdapter extends BaseAdapter {
    private Context context=null;
    private int layout;
    private ArrayList<Dish> monAnList=null;


    public DishAdapter(Context context, int layout, ArrayList<Dish> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }
    @Override
    public int getCount(){
        return monAnList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.dish, null,
                            false);
        }

        Dish monan = monAnList.get(position);
        ImageView imgMonan = (ImageView) convertView.findViewById(R.id.imgMonan);
        TextView txtMonan = (TextView) convertView.findViewById(R.id.txtMonan);
        txtMonan.setSelected(true);
        ImageView icnStar = (ImageView) convertView.findViewById(R.id.icnStar);

        if (monan.isPromotion())
        {
            icnStar.setVisibility(View.VISIBLE);

        }
        else
        {
            icnStar.setVisibility(View.GONE);
        }

        imgMonan.setImageResource(monan.getThumbnail());
        txtMonan.setText(monan.getName());
        return convertView;
    }
}
