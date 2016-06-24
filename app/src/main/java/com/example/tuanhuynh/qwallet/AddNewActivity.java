package com.example.tuanhuynh.qwallet;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.adapter.CateloryAdapter;
import com.example.tuanhuynh.qwallet.adapter.ItemFinanceAdapter;
import com.example.tuanhuynh.qwallet.database.CateloryDatabaseHelper;
import com.example.tuanhuynh.qwallet.database.ItemDatabaseHelper;
import com.example.tuanhuynh.qwallet.objects.Catelories;
import com.example.tuanhuynh.qwallet.objects.ItemFinance;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AddNewActivity extends AppCompatActivity {

    private GridView gridView;
    private String choose;
    private String action = "income";

    private DatePicker datePicker;
    private Calendar calendar;
    private EditText dateView;
    private int year, month, day;
    private Spinner spinnerCatelory;
    private CateloryAdapter cateloryAdapter;
    private List<Catelories> listOfSpinner = new ArrayList<Catelories>();
    private TextView btn_income;
    private TextView btn_expense;
    private ArrayList<String> valueMoney;
    private EditText editName;
    private EditText editDate;
    private CateloryDatabaseHelper cateloryDatabaseHelper;
    private TextView btn_1;
    private TextView btn_2;
    private TextView btn_3;
    private TextView btn_4;
    private TextView btn_5;
    private TextView btn_6;
    private TextView btn_7;
    private TextView btn_8;
    private TextView btn_9;
    private TextView btn_0;
    private TextView btn_dot;
    private TextView btn_OK;
    private ImageView btn_backspace;
    private TextView txtSetMoney;

    public AddNewActivity() {
    }

    @Override
    public void onBackPressed() {
        if(choose.equals("edit")){
            Intent in = new Intent(AddNewActivity.this, MainActivity.class);
            startActivity(in);
            finish();
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
//
        choose = "add";
        Intent intent = getIntent();
        if(intent.getStringExtra("choose")!=null){
            choose = intent.getStringExtra("choose");
        }
        final ItemFinance itemSelected = (ItemFinance)intent.getSerializableExtra("item");
//        Toast.makeText(AddNewActivity.this, choose, Toast.LENGTH_LONG).show();

        btn_income = (TextView)findViewById(R.id.txt_income);
        btn_expense = (TextView)findViewById(R.id.txt_expense);
        txtSetMoney = (TextView)findViewById(R.id.txt_set_money);

        final Resources res = null;
        final TextView set000 = (TextView)findViewById(R.id.txt_set_000);
        if(choose.equals("edit")){
            action = itemSelected.getType();
            if(action.equals("income")){
                btn_income.setBackgroundResource(R.drawable.incom_button_selected);
                btn_expense.setBackgroundResource(R.drawable.expense_button_selector);
                txtSetMoney.setTextColor(getResources().getColor(R.color.colorIncome));
                set000.setTextColor(getResources().getColor(R.color.colorIncome));
            } else{
                btn_expense.setBackgroundResource(R.drawable.expense_button_selected);
                btn_income.setBackgroundResource(R.drawable.incom_button_selector);
                txtSetMoney.setTextColor(getResources().getColor(R.color.colorExpense));
                set000.setTextColor(getResources().getColor(R.color.colorExpense));
            }
        }
        btn_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "income";
                if(itemSelected!=null){ itemSelected.setType(action);}
                btn_income.setBackgroundResource(R.drawable.incom_button_selected);
                btn_expense.setBackgroundResource(R.drawable.expense_button_selector);
                txtSetMoney.setTextColor(getResources().getColor(R.color.colorIncome));
                set000.setTextColor(getResources().getColor(R.color.colorIncome));
                //Toast.makeText(AddNewActivity.this, "INCOME", Toast.LENGTH_SHORT).show();
            }
        });
        btn_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "expense";
                if(itemSelected!=null){ itemSelected.setType(action);}
                btn_expense.setBackgroundResource(R.drawable.expense_button_selected);
                btn_income.setBackgroundResource(R.drawable.incom_button_selector);
                txtSetMoney.setTextColor(getResources().getColor(R.color.colorExpense));
                set000.setTextColor(getResources().getColor(R.color.colorExpense));
                //Toast.makeText(AddNewActivity.this, "EXPENSE", Toast.LENGTH_SHORT).show();
            }
        });

        dateView = (EditText) findViewById(R.id.edit_date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        if(choose.equals("edit")){
            String day = itemSelected.getDate();
            dateView.setText(day);
            int d,m,y;
            d=Integer.parseInt(getIntDate(itemSelected.getDate()).get(0));
            m=Integer.parseInt(getIntDate(itemSelected.getDate()).get(1));
            y=Integer.parseInt(getIntDate(itemSelected.getDate()).get(2));
            calendar.set(y,m,d);
            showDate(y,m,d);
        } else {
            showDate(year, month + 1, day);
        }

        cateloryDatabaseHelper = new CateloryDatabaseHelper(getBaseContext());
        cateloryDatabaseHelper.createDefaultToTest();
        List<Catelories> list = new ArrayList<Catelories>();
        list =  cateloryDatabaseHelper.getAll();
        ArrayList<String> listName = new ArrayList<String>();
        ArrayList<String> listIcon = new ArrayList<String>();

        for(Catelories cate : list){
            listName.add(cate.getName());
            listIcon.add(cate.getIconName());
        }

        String[] names = new String[listName.size()];
        names = listName.toArray(names);

        String[] icons = new String[listName.size()];
        icons = listName.toArray(icons);

        //Catelories[] listCate = (Catelories[])list.toArray();
        //==================================================================================Spinner================================
        listOfSpinner.addAll(list);

        spinnerCatelory = (Spinner)findViewById(R.id.spinner_catelory);

        CateloryAdapter cateloryAdapter = new CateloryAdapter(getApplicationContext(),names,icons);
        spinnerCatelory.setAdapter(cateloryAdapter);
        if(choose.equals("edit")){
            spinnerCatelory.setSelection(itemSelected.getCategoryID()-1);
        }
        //=====================================================================================================================

        if(choose.equals("edit")){
            String valMon = String.valueOf(itemSelected.getMoney());
            ArrayList<String> myArraySubstrings = new ArrayList<String>();
            for(int i =0; i < valMon.length()-3; i++)
                myArraySubstrings.add(valMon.substring(i, i+1));
            valueMoney = myArraySubstrings;
            txtSetMoney.setText(convertToString(valueMoney));
        }else valueMoney= new ArrayList<String>();

        btn_0 = (TextView)findViewById(R.id.btn_0);
        btn_1 = (TextView)findViewById(R.id.btn_1);
        btn_2 = (TextView)findViewById(R.id.btn_2);
        btn_3 = (TextView)findViewById(R.id.btn_3);
        btn_4 = (TextView)findViewById(R.id.btn_4);
        btn_5 = (TextView)findViewById(R.id.btn_5);
        btn_6 = (TextView)findViewById(R.id.btn_6);
        btn_7 = (TextView)findViewById(R.id.btn_7);
        btn_8 = (TextView)findViewById(R.id.btn_8);
        btn_9 = (TextView)findViewById(R.id.btn_9);
        btn_dot = (TextView)findViewById(R.id.btn_dot);
        btn_OK = (TextView)findViewById(R.id.btn_OK);
        btn_backspace = (ImageView)findViewById(R.id.btn_backspace);
        txtSetMoney = (TextView)findViewById(R.id.txt_set_money);

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valueMoney.size()!=0){
                    valueMoney.add(valueMoney.size(),"0");
                    txtSetMoney.setText(convertToString(valueMoney));
                }
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"1");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"2");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"3");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"4");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"5");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"6");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"7");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"8");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(),"9");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueMoney.add(valueMoney.size(), ",");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueMoney.size() > 0) {
                    valueMoney.remove(valueMoney.size() - 1);
                }
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });

        editName = (EditText)findViewById(R.id.edit_name);
        if(choose.equals("edit")){
            editName.setText(itemSelected.getTitle());
        }
        editDate = (EditText)findViewById(R.id.edit_date);
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(valueMoney.size()>11){
                    new AlertDialog.Builder(AddNewActivity.this)
                            .setTitle("Wrong number input")
                            .setMessage("Your money is too big!")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).create().show();
                }else {
                    String nameOfnote = editName.getText().toString();
                    String date = editDate.getText().toString();
                    String catelory = spinnerCatelory.getSelectedItem().toString();
                    String valueOfMoney = "";
                    for (String s : valueMoney) {
                        valueOfMoney += s;
                    }
                    valueOfMoney = valueOfMoney + "000";
                    Long money = Long.parseLong(valueOfMoney);

                    ItemDatabaseHelper db = new ItemDatabaseHelper(getApplicationContext());
                    List<ItemFinance> listToCheck = db.getAll();
                    int idOfItem = listToCheck.get(listToCheck.size() - 1).getId();
                    //int a;

                    if (choose.equals("edit")) {
                        ItemFinance item = new ItemFinance(itemSelected.getId(), action, nameOfnote, date, money, getCateloryId(catelory));
                        db.updateFinance(item);
                        //Toast.makeText(AddNewActivity.this, db.updateFinance(item)+"", Toast.LENGTH_SHORT).show();
                    } else {
//                    Toast.makeText(AddNewActivity.this, getCateloryId(catelory)+"", Toast.LENGTH_SHORT).show();
                        ItemFinance item = new ItemFinance(idOfItem + 1, action, nameOfnote, date, money, getCateloryId(catelory));
                        db.addFinance(item);
                    }
                    Intent in = new Intent(AddNewActivity.this, MainActivity.class);
                    startActivity(in);
                    finish();
                }
            }
        });


    }

    int getCateloryId(String s){
        CateloryDatabaseHelper dbCate = new CateloryDatabaseHelper(getApplicationContext());
        return dbCate.getCateID(s);
    }
    String convertToString(ArrayList<String> list){
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

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
//        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        if(month<10){
            if(day<10){
                dateView.setText(new StringBuilder().append("0").append(day).append("/0")
                        .append(month).append("/").append(year));
            } else dateView.setText(new StringBuilder().append(day).append("/0")
                    .append(month).append("/").append(year));
        }
        else dateView.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
    }

    List<String> getIntDate(String dateInput){
        List<String> dateSplit = new ArrayList<>(Arrays.asList(dateInput.split("/")));
        return dateSplit;
    }
//    int getIntMonth(String dateInput){
//        List<String> dateSplit = new ArrayList<>(Arrays.asList(dateInput.split("/")));
//        String monthString = dateSplit.get(1);
//        return Integer.parseInt(monthString);
//    }
//    int getIntYear(String dateInput){
//        List<String> dateSplit = new ArrayList<>(Arrays.asList(dateInput.split("/")));
//        String yearString = dateSplit.get(2);
//        return Integer.parseInt(yearString);
//    }
}
