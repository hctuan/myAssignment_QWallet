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
import com.example.tuanhuynh.qwallet.objects.Item;

/**
 * Created by YobboPEL on 18/06/2016.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    Context context;

    public ItemAdapter(Context context, int resourceId,
                       List<Item> items) {
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

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Item rowItem = getItem(position);

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

        holder.txtDate.setText(rowItem.getdate());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.txtMoney.setText(rowItem.getTitle());
        holder.imageView.setImageResource(rowItem.getImageId());

        return convertView;
    }

}
