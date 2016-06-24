package com.example.tuanhuynh.qwallet.adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.AddNewActivity;
import com.example.tuanhuynh.qwallet.MainActivity;
import com.example.tuanhuynh.qwallet.R;
import com.example.tuanhuynh.qwallet.database.CateloryDatabaseHelper;
import com.example.tuanhuynh.qwallet.database.ItemDatabaseHelper;
import com.example.tuanhuynh.qwallet.objects.ItemFinance;

/**
 * Created by YobboPEL on 18/06/2016.
 */
public class ItemFinanceAdapter extends ArrayAdapter<ItemFinance> {

    Context context;
    List<ItemFinance> items;

    public ItemFinanceAdapter(Context context, int resourceId,
                              List<ItemFinance> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDate;
        TextView txtMoney;
        ImageView edit;
        ImageView delete;
    }

    public List<ItemFinance> getData() {
        return items;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final ItemFinance rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtDate = (TextView) convertView.findViewById(R.id.date);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.txtMoney = (TextView) convertView.findViewById(R.id.money);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            holder.delete = (ImageView) convertView.findViewById(R.id.img_delete);
            holder.edit = (ImageView) convertView.findViewById(R.id.img_edit);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtDate.setText(rowItem.getDate());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.txtMoney.setText(convertToMoneyString(String.valueOf(rowItem.getMoney()))+" VND");
        if(rowItem.getType().equals("income")){
            holder.txtMoney.setTextColor(getContext().getResources().getColor(R.color.selected_day_background));
        } else holder.txtMoney.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
        CateloryDatabaseHelper dbCate = new CateloryDatabaseHelper(getContext());
        String cate = dbCate.getCate(rowItem.getCategoryID());
        holder.imageView.setImageResource(getImageId(cate));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemFinance itemF = new ItemFinance();
                itemF = rowItem;
                Intent in = new Intent(getContext(), AddNewActivity.class);
                in.putExtra("choose", "edit");
                in.putExtra("item", itemF);
                getContext().startActivity(in);
                MainActivity.fa.finish();
            }
        });

        final View finalConvertView = convertView;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDatabaseHelper db = new ItemDatabaseHelper(getContext());
                db.deleteNote(rowItem);
                ItemFinanceAdapter.this.notifyDataSetChanged();
            }
        });
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
            case "school":
                return R.drawable.school;
            case "bank":
                return R.drawable.bank;
            case "baby":
                return R.drawable.baby;
            case "save":
                return R.drawable.save;
            case "gas":
                return R.drawable.gas;
            default:
                return R.drawable.other;
        }
    }

    String convertToMoneyString(String strInput){

        ArrayList<String> list = new ArrayList<String>();
        for(int i =0; i < strInput.length(); i++)
            list.add(strInput.substring(i, i+1));

        String listString = "";

        if(list.size()==0){
            return listString+"0";
        }
        int temp=2-list.size()%3;
        if(list.size()%3==0){
            temp -=3;
        }
        for (String s : list)
        {
            temp++;
            if(temp==3){
                listString += ",";
                listString += s;
                temp=0;
            }else listString += s;
        }
        return listString;
    }

}
