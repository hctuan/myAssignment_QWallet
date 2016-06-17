package com.example.tuanhuynh.qwallet.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.R;

/**
 * Created by tuan.huynh on 6/16/2016.
 */
public class CustomAdapterPasscode extends BaseAdapter {
    private Context mContext;
    private final String[] numlist;
    //private final int[] Imageid;

    public CustomAdapterPasscode(Context c,String[] numlist ) {
        mContext = c;
        //this.Imageid = Imageid;
        this.numlist = numlist;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return numlist.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.circular_button_layout, null);
            TextView textView = (TextView) grid.findViewById(R.id.txt_number);
            //ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(numlist[position]);
            if(numlist[position]==""){
                //textView.setAlpha(0.0f);  //set trong suốt
                textView.setVisibility(textView.GONE);
                //textView.isClickable();
            }
            //imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
