package com.example.tuanhuynh.qwallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuanhuynh.qwallet.R;
import com.example.tuanhuynh.qwallet.objects.ItemReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuan.huynh on 6/23/2016.
 */
public class ReportAdapter extends BaseAdapter {

    Context mContext;
    List<ItemReport> mList;

    public ReportAdapter(Context context, List<ItemReport> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
    public int getItemViewType(int position) {
        return (mList.get(position).isTitle())?0:1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        int type = getItemViewType(position);
        if(v==null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(type == 0){
                v = inflater.inflate(R.layout.report_catelory_layout,parent,false);
            } else
            {
                v = inflater.inflate(R.layout.report_detail_layout,parent,false);
            }
        }

        ItemReport itemReport = mList.get(position);
        if(type == 0){
            ImageView imgCate = (ImageView)v.findViewById(R.id.img_report_catelory);
            TextView tvCate = (TextView)v.findViewById(R.id.tv_report_catelory);
            TextView tvBalance = (TextView)v.findViewById(R.id.tv_report_balance);

            imgCate.setImageResource(getImageId(itemReport.getCate()));
            tvCate.setText(itemReport.getCate());
            tvBalance.setText(convertToMoneyString(String.valueOf(itemReport.getValue()))+ " VND");
            if(itemReport.getInOrEx().equals("income")){
                tvBalance.setTextColor(v.getResources().getColor(R.color.colorIncome));
            } else tvBalance.setTextColor(v.getResources().getColor(R.color.colorExpense));
        } else{
            TextView tvTitle = (TextView)v.findViewById(R.id.tv_repport_detail_title);
            TextView tvDate = (TextView)v.findViewById(R.id.tv_report_detail_date);
            TextView tvMoney = (TextView)v.findViewById(R.id.tv_report_detail_money);
            tvTitle.setText(itemReport.getNote());
            tvDate.setText(itemReport.getDate());
            tvMoney.setText(convertToMoneyString(String.valueOf(itemReport.getValue()))+ " VND");
            if(itemReport.getInOrEx().equals("income")){
                tvMoney.setTextColor(v.getResources().getColor(R.color.colorIncome));
            } else tvMoney.setTextColor(v.getResources().getColor(R.color.colorExpense));
        }
        return v;
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
            case "hospital":
                return R.drawable.hospital;
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
        if(list.get(0).equals("-")){
            temp -=3;
        }
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
