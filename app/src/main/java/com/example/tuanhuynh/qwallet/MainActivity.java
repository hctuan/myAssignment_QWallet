package com.example.tuanhuynh.qwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.adapter.ItemFinanceAdapter;
import com.example.tuanhuynh.qwallet.database.ItemDatabaseHelper;
import com.example.tuanhuynh.qwallet.fragment.SummaryDialog;
import com.example.tuanhuynh.qwallet.objects.ItemFinance;
import com.samsistemas.calendarview.widget.CalendarView;
import com.samsistemas.calendarview.widget.DayView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends ActionBarActivity implements
    AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private  List<ItemFinance> rowItems = new ArrayList<ItemFinance>();
    private CalendarView calendarView;
    private String dateSelected;
    private ItemDatabaseHelper db;
    private ItemFinanceAdapter adapter;
    private ImageView imgReport;
    private List<ItemFinance> itemFinanceList;
    public static Activity fa;
    private List<ItemFinance> listOfMonth;
    String monthView;
    String yearView;

    //Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fa = this;

        //tạo database và thêm vào dữ liệu mặc định
        db = new ItemDatabaseHelper(this);
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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date(System.currentTimeMillis());
        String todayString = df.format(currentDate);
        final DayView dayView = calendarView.findViewByDate(new Date(System.currentTimeMillis()));
        if(null != dayView)
            dateSelected = todayString;

        //Toast.makeText(MainActivity.this,today, Toast.LENGTH_SHORT).show();
        //lấy tất cả dữ liệu đổ vào list
        //List<ItemFinance> list = db.getAll();
        //Lấy dữ liệu theo ngày
        itemFinanceList = db.getByDate(dateSelected);
        this.rowItems.addAll(itemFinanceList);

        listView = (ListView) findViewById(R.id.list);
        adapter = new ItemFinanceAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat  = new SimpleDateFormat("dd/MM/yyyy");
        monthView = String.valueOf(today.getMonth()+1);
        if(monthView.length()<2){
            monthView = "0"+monthView;
        }
        yearView = calendarView.getCurrentYear();
        listOfMonth = db.getByMonth(monthView,yearView);
        //========================================change month=============================================================
        calendarView.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(@NonNull Date date) {
                SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
                String dmy = simple.format(date);
                monthView = String.valueOf(date.getMonth() + 1);
                if (monthView.length() < 2) {
                    monthView = "0" + monthView;
                }
                yearView = String.valueOf(date.getYear() + 1900);

                listOfMonth = db.getByMonth(monthView,yearView);
                //Toast.makeText(MainActivity.this, dmy +" "+ monthView + " " + yearView, Toast.LENGTH_SHORT).show();
            }
        });
        //============================================================================================================

        //======================================================================================[ button Report]=================================

        imgReport = (ImageView)findViewById(R.id.img_report);
        imgReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("month", "aaaaa");
                long sumIncome = 0;
                long sumExpense = 0;
                for (ItemFinance iFinance : listOfMonth) {
                    if (iFinance.getType().equals("income")) {
                        sumIncome += iFinance.getMoney();
                    } else {
                        sumExpense += iFinance.getMoney();
                    }
                }
                data.putLong("income", sumIncome);
                data.putLong("expense", sumExpense);
                data.putString("title", monthView);
                data.putString("year",yearView);
                SummaryDialog summaryDialog = new SummaryDialog();
                summaryDialog.setArguments(data);
                summaryDialog.show(getSupportFragmentManager(), "Summary of month");
            }
        });
        //=====================================================================================================================
    }

    public void reloadListview(){
        // get new modified random data
        List<ItemFinance> newList = db.getByDate(dateSelected);
        // update data in our adapter
        this.adapter.clear();
        this.adapter.addAll(newList);
        // fire the event
        //if(newList.size()<1)
            //Toast.makeText(getBaseContext(), "NULL", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
//        Toast toast = Toast.makeText(getApplicationContext(),
//                "Item " + (position + 1) + ": " + rowItems.get(position).getMoney(),
//                Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
//        toast.show();
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

    private static MainActivity mainActivity = null;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
    //long click item on listview
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, "long clicked pos: " + position, Toast.LENGTH_LONG).show();

        ImageView imgDelete = (ImageView)view.findViewById(R.id.img_delete);
        ImageView imgEdit = (ImageView)view.findViewById(R.id.img_edit);

        //imgDelete.setVisibility(View.VISIBLE);
        imgDelete.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //imgEdit.setVisibility(View.VISIBLE);
        imgEdit.setBackgroundColor(getResources().getColor(R.color.colorGray));
        //Toast.makeText(this, "long clicked pos: " , Toast.LENGTH_LONG).show();
        LinearLayout lin = (LinearLayout)findViewById(R.id.layout_item);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_to_left);
        //lin.startAnimation(animation);
        //view.startAnimation(animation);
        imgDelete.startAnimation(animation);
        imgEdit.startAnimation(animation);
        return false;
    }
}