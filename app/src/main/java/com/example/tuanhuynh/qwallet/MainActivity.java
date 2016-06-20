package com.example.tuanhuynh.qwallet;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements
    AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView listView;
    List<ItemFinance> rowItems = new ArrayList<ItemFinance>();


    //Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tạo database và thêm vào dữ liệu mặc định
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultToTest();
        //lấy tất cả dữ liệu đổ vào list
        //List<ItemFinance> list = db.getAll();
        //Lấy dữ liệu theo ngày
        List<ItemFinance> list = db.getByDate("5/5/2015");
        this.rowItems.addAll(list);

        listView = (ListView) findViewById(R.id.list);
        ItemFinanceAdapter adapter = new ItemFinanceAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
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