package com.example.tuanhuynh.qwallet.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tuanhuynh.qwallet.R;
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
    }

    public List<ItemFinance> getData() {
        return items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ItemFinance rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtDate = (TextView) convertView.findViewById(R.id.date);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.txtMoney = (TextView) convertView.findViewById(R.id.money);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtDate.setText(rowItem.getDate());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.txtMoney.setText(String.valueOf(rowItem.getMoney()));
        holder.imageView.setImageResource(getImageId(rowItem.getType()));

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
