package com.example.tuanhuynh.qwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.adapter.ReportAdapter;
import com.example.tuanhuynh.qwallet.database.CateloryDatabaseHelper;
import com.example.tuanhuynh.qwallet.database.ItemDatabaseHelper;
import com.example.tuanhuynh.qwallet.objects.ItemFinance;
import com.example.tuanhuynh.qwallet.objects.ItemReport;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    List<ItemFinance> listItem;
    int haveIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent in = getIntent();
        String month = in.getStringExtra("month");
        String year = in.getStringExtra("year");
        //Toast.makeText(ReportActivity.this, month+year, Toast.LENGTH_SHORT).show();

        ItemDatabaseHelper db = new ItemDatabaseHelper(this);
        listItem = db.getByMonth(month,year);
        //ItemFinance iFinance = new ItemFinance();
        ItemReport iReport;
        List<ItemReport> listReport = new ArrayList<ItemReport>();
        int temp = 0;
        CateloryDatabaseHelper dbCate = new CateloryDatabaseHelper(this);
        for(ItemFinance i : listItem){
            if(i!=null) {
                if (listReport.size() == 0) {
                    temp = i.getCategoryID();
                    long val = getValueByCateloryID(i.getCategoryID());
                    iReport = new ItemReport(true, getCatelory(listItem.get(0).getCategoryID()),val, null, null, checkBalance(haveIncome));
                    listReport.add(iReport);
                    iReport = new ItemReport(false, dbCate.getCate(i.getCategoryID()),i.getMoney(), i.getTitle(), i.getDate(),i.getType() );
                    listReport.add(iReport);
                } else {
                    if(temp!=i.getCategoryID()){
                        long val = getValueByCateloryID(i.getCategoryID());
                        iReport = new ItemReport(true, dbCate.getCate(i.getCategoryID()),val, null, null, checkBalance(haveIncome));
                        listReport.add(iReport);
                        iReport = new ItemReport(false, dbCate.getCate(i.getCategoryID()),i.getMoney(), i.getTitle(), i.getDate(),i.getType() );
                        listReport.add(iReport);
                        temp=i.getCategoryID();
                    } else{
                        iReport = new ItemReport(false, dbCate.getCate(i.getCategoryID()),i.getMoney(), i.getTitle(), i.getDate(),i.getType() );
                        listReport.add(iReport);
                        temp=i.getCategoryID();
                    }
                }
            }
        }

        ReportAdapter adapterReport = new ReportAdapter(this,listReport);
        ListView lvReport = (ListView)findViewById(R.id.lv_report);
        lvReport.setAdapter(adapterReport);

        //Toast.makeText(ReportActivity.this, "      asdas    ", Toast.LENGTH_SHORT).show();
    }

    String getInOrEx(long values){ return "";}

    private long getValueByCateloryID(int cateId){
        long sumValue = 0;
        haveIncome = 0;
        for(ItemFinance i : listItem){
            if(i.getCategoryID()==cateId){
                if(i.getType().equals("income")) {
                    haveIncome=1;
                    sumValue += i.getMoney();
                }else{
                    sumValue -= i.getMoney();
                }
            }
        }
        if(haveIncome==1){
            if(sumValue<0) {
                haveIncome=0;
            }
            return sumValue;
        } else {
            sumValue = sumValue * (-1);
            return sumValue;
        }
    }

    String checkBalance(int check){
        return (check==1)?"income":"expense";
    }

    private String getCatelory(int i){
        CateloryDatabaseHelper dbCate = new CateloryDatabaseHelper(getApplicationContext());
        return dbCate.getCate(i);
//            1:"shopping";2:"cinema";3:"salary";4:"party";5:"school";6:"bank";7:"baby";8:"save";9:"gas";10:"other";
    }
}
