package com.example.tuanhuynh.qwallet;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.adapter.ItemFinanceAdapter;
import com.example.tuanhuynh.qwallet.database.MyDatabaseHelper;
import com.example.tuanhuynh.qwallet.objects.ItemFinance;
import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.calendarview.widget.DayView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends ActionBarActivity implements
    AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView listView;
    List<ItemFinance> rowItems = new ArrayList<ItemFinance>();
    CalendarView calendarView;
    String dateSelected;
    MyDatabaseHelper db;
    ItemFinanceAdapter adapter;


    //Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tạo database và thêm vào dữ liệu mặc định
        db = new MyDatabaseHelper(this);
        db.createDefaultToTest();

        calendarView = (CalendarView) findViewById(R.id.calendar_view);

        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setIsOverflowDateVisible(true);
        calendarView.setCurrentDay(new Date(System.currentTimeMillis()));
        calendarView.setBackButtonColor(R.color.colorAccent);
        calendarView.setNextButtonColor(R.color.colorAccent);
        calendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull Date selectedDate) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                dateSelected = df.format(selectedDate);
                Toast.makeText(getBaseContext(), dateSelected, Toast.LENGTH_LONG).show();
                reloadListview();
            }

//            @Override
//            public void onDateClick(@NonNull Date selectedDate) {
//                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//                //textView.setText(df.format(selectedDate));
//            }
        });

//        calendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
//            @Override
//            public void onMonthChanged(@NonNull Date monthDate) {
//                SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
//                if (null != actionBar)
//                    actionBar.setTitle(df.format(monthDate));
//            }
//        });
        final DayView dayView = calendarView.findViewByDate(new Date(System.currentTimeMillis()));
        if(null != dayView)
            dateSelected = dayView.getText().toString() + "/" + calendarView.getCurrentMonth() + "/" +  calendarView.getCurrentYear();
            Toast.makeText(getApplicationContext(), "Today is: " + dayView.getText().toString() + "/" + calendarView.getCurrentMonth() + "/" +  calendarView.getCurrentYear(), Toast.LENGTH_SHORT).show();

        //lấy tất cả dữ liệu đổ vào list
        //List<ItemFinance> list = db.getAll();
        //Lấy dữ liệu theo ngày
        List<ItemFinance> list = db.getByDate(dateSelected);
        this.rowItems.addAll(list);

        listView = (ListView) findViewById(R.id.list);
        adapter = new ItemFinanceAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    private void reloadListview(){
        // get new modified random data
        List<ItemFinance> newList = db.getByDate(dateSelected);
        // update data in our adapter
        this.adapter.clear();
        this.adapter.addAll(newList);
        // fire the event
        if(newList.size()<1)
            Toast.makeText(getBaseContext(), "NULL", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
                addNew();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNew() {
        openAddNewActivity();
    }

    public void openAddNewActivity()
    {
        Intent myIntent=new Intent(this, AddNewActivity.class);
        startActivity(myIntent);
        //finish();
    }

    //long click item on listview
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "long clicked pos: " + position, Toast.LENGTH_LONG).show();
        ImageView imgDelete = (ImageView)view.findViewById(R.id.img_delete);
        ImageView imgEdit = (ImageView)view.findViewById(R.id.img_edit);

        imgDelete.setVisibility(View.VISIBLE);
        imgDelete.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        imgEdit.setVisibility(View.VISIBLE);
        imgEdit.setBackgroundColor(getResources().getColor(R.color.colorGray));
        //Toast.makeText(this, "long clicked pos: " , Toast.LENGTH_LONG).show();
        return false;
    }
}