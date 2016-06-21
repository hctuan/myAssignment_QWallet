package com.example.tuanhuynh.qwallet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.R;
import com.example.tuanhuynh.qwallet.objects.Catelories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuan.huynh on 6/21/2016.
 */
public class CateloryAdapter extends BaseAdapter {


    Context mContext;
    String[] mName;
    String[] mIcon;

    public CateloryAdapter(Context context, String[] name, String[] icon) {
        this.mName = name;
        this.mIcon=icon;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mName.length;
    }

    @Override
    public Object getItem(int position) {
        return mName[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.catelory_item_spinner, null);
        ImageView icon = (ImageView) convertView.findViewById(R.id.img_catelory);
        TextView names = (TextView) convertView.findViewById(R.id.txt_name_catelory);
        icon.setImageResource(getImageId(mIcon[position]));
        //Toast.makeText(mContext, mName[position], Toast.LENGTH_LONG).show();
        names.setText(mName[position]);
        return convertView;
    }

    private int getImageId(String type){
        switch(type){
            case "shopping":
                return R.drawable.shopping;
            case "cinema":
                return R.drawable.cinema;
            case "salary":
                return R.drawable.salary;
            case "party":
                return R.drawable.party;
            default:
                return R.drawable.other;
        }
    }
}
