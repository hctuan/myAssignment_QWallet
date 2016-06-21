package com.example.tuanhuynh.qwallet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.tuanhuynh.qwallet.database.CateloryDatabaseHelper;
import com.example.tuanhuynh.qwallet.database.ItemDatabaseHelper;
import com.example.tuanhuynh.qwallet.objects.Catelories;
import com.example.tuanhuynh.qwallet.objects.ItemFinance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddNewActivity extends AppCompatActivity {

    private GridView gridView;
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
    ArrayList<String> valueMoney;
    EditText editName;
    EditText editDate;
    CateloryDatabaseHelper cateloryDatabaseHelper;
    TextView btn_1;
    TextView btn_2;
    TextView btn_3;
    TextView btn_4;
    TextView btn_5;
    TextView btn_6;
    TextView btn_7;
    TextView btn_8;
    TextView btn_9;
    TextView btn_0;
    TextView btn_dot;
    TextView btn_OK;
    ImageView btn_backspace;
    TextView txtSetMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        btn_income = (TextView)findViewById(R.id.txt_income);
        btn_expense = (TextView)findViewById(R.id.txt_expense);
        txtSetMoney = (TextView)findViewById(R.id.txt_set_money);
        final Resources res = null;
        btn_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "income";
                btn_income.setBackgroundResource(R.drawable.incom_button_selected);
                btn_expense.setBackgroundResource(R.drawable.expense_button_selector);
                txtSetMoney.setTextColor(getResources().getColor(R.color.colorIncome));
                Toast.makeText(AddNewActivity.this, "INCOME", Toast.LENGTH_SHORT).show();
            }
        });
        btn_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = "expense";
                btn_expense.setBackgroundResource(R.drawable.expense_button_selected);
                btn_income.setBackgroundResource(R.drawable.incom_button_selector);
                txtSetMoney.setTextColor(getResources().getColor(R.color.colorExpense));
                Toast.makeText(AddNewActivity.this, "EXPENSE", Toast.LENGTH_SHORT).show();
            }
        });

        dateView = (EditText) findViewById(R.id.edit_date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

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
        listOfSpinner.addAll(list);

        spinnerCatelory = (Spinner)findViewById(R.id.spinner_catelory);

        CateloryAdapter cateloryAdapter = new CateloryAdapter(getApplicationContext(),names,icons);
        spinnerCatelory.setAdapter(cateloryAdapter);

        valueMoney= new ArrayList<String>();

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
                valueMoney.add(valueMoney.size(),"0");
                txtSetMoney.setText(convertToString(valueMoney));
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
                valueMoney.add(valueMoney.size(),",");
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        btn_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valueMoney.size()>0){
                    valueMoney.remove(valueMoney.size()-1);
                }
                txtSetMoney.setText(convertToString(valueMoney));
            }
        });
        editName = (EditText)findViewById(R.id.edit_name);
        editDate = (EditText)findViewById(R.id.edit_date);
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOfnote = editName.getText().toString();
                String date = editDate.getText().toString();
                String catelory = spinnerCatelory.getSelectedItem().toString();
                String valueOfMoney = "";
                for (String s : valueMoney)
                {
                    valueOfMoney += s;
                }
                double money = Double.parseDouble(valueOfMoney);
                ItemFinance item = new ItemFinance(catelory,nameOfnote,date,money,getCateloryId(catelory));
                ItemDatabaseHelper db = new ItemDatabaseHelper(getApplicationContext());
                db.addFinance(item);
                Toast.makeText(AddNewActivity.this, catelory, Toast.LENGTH_SHORT).show();
            }
        });


    }

    int getCateloryId(String s){
        switch (s) {
            case "shopping":
                return 1;
            case "cinema":
                return 2;
            case "salary":
                return 3;
            case "party":
                return 4;
            case "other":
                return 5;
            default:
                return 5;
        }
    }
    String convertToString(ArrayList<String> list){
        String listString = "";

        if(list.size()==0){
            return listString+"0,000 VND";
        }

        for (String s : list)
        {
            listString += s;
        }
        return listString+",000 VND";
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
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
}
