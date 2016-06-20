package com.example.tuanhuynh.qwallet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tuanhuynh.qwallet.objects.ItemFinance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuan.huynh on 6/20/2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "FinanceData";


    // Tên bảng: Note.
    private static final String TABLE_FINANCE = "FinanceTable";

    private static final String COLUMN_FINANCE_ID ="Finance_Id";
    private static final String COLUMN_FINANCE_NOTE ="Finance_Note";
    private static final String COLUMN_FINANCE_DATE = "Finance_Date";
    private static final String COLUMN_FINANCE_MONEY = "Finance_Money";
    private static final String COLUMN_FINANCE_TYPE = "Finance_Type";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_FINANCE + "("
                + COLUMN_FINANCE_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_FINANCE_TYPE + " TEXT,"
                + COLUMN_FINANCE_NOTE + " TEXT,"
                + COLUMN_FINANCE_DATE + " TEXT,"
                + COLUMN_FINANCE_MONEY+ " TEXT" + ")";
        //String script = "CREATE TABLE FinanceTable (Finance_Id INTEGER PRIMARY KEY,Finance_Type TEXT,Finance_Note TEXT, Finance_Date TEXT, Finance_Money TEXT) ";
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINANCE);

        // Và tạo lại.
        onCreate(db);
    }

    public void addFinance(ItemFinance finance) {
        Log.i(TAG, "MyDatabaseHelper.addFinance ... " + finance.getTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FINANCE_NOTE, finance.getTitle());
        values.put(COLUMN_FINANCE_MONEY, String.valueOf(finance.getMoney()));
        values.put(COLUMN_FINANCE_DATE, finance.getDate());
        values.put(COLUMN_FINANCE_TYPE, finance.getType());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_FINANCE, null, values);


        // Đóng kết nối database.
        db.close();
    }
    public ItemFinance getFinance(int id) {
        Log.i(TAG, "MyDatabaseHelper.getFinance ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FINANCE, new String[] { COLUMN_FINANCE_ID,COLUMN_FINANCE_TYPE,
                        COLUMN_FINANCE_NOTE, COLUMN_FINANCE_DATE,COLUMN_FINANCE_MONEY }, COLUMN_FINANCE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ItemFinance finance = new ItemFinance(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Double.parseDouble(cursor.getString(4)));
        // return note
        return finance;
    }

    public List<ItemFinance> getByDate(String date) {
        Log.i(TAG, "MyDatabaseHelper.getByDate ... " );

        String DATE_SELECT = date;
        List<ItemFinance> list = new ArrayList<ItemFinance>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FINANCE + " WHERE "+ COLUMN_FINANCE_DATE + " = '" + DATE_SELECT + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                ItemFinance finance = new ItemFinance();
                finance.setId(Integer.parseInt(cursor.getString(0)));
                finance.setType(cursor.getString(1));
                finance.setTitle(cursor.getString(2));
                finance.setDate(cursor.getString(3));
                finance.setMoney(Double.parseDouble(cursor.getString(4)));

                // Thêm vào danh sách.
                list.add(finance);
            } while (cursor.moveToNext());
        }

        // return note list
        return list;
    }

    public List<ItemFinance> getAll() {
        Log.i(TAG, "MyDatabaseHelper.getAll ... " );

        List<ItemFinance> list = new ArrayList<ItemFinance>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FINANCE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                ItemFinance finance = new ItemFinance();
                finance.setId(Integer.parseInt(cursor.getString(0)));
                finance.setType(cursor.getString(1));
                finance.setTitle(cursor.getString(2));
                finance.setDate(cursor.getString(3));
                finance.setMoney(Double.parseDouble(cursor.getString(4)));

                // Thêm vào danh sách.
                list.add(finance);
            } while (cursor.moveToNext());
        }

        // return note list
        return list;
    }

    public int getCount() {
        Log.i(TAG, "MyDatabaseHelper.getCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_FINANCE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updateFinance(ItemFinance finance) {
        Log.i(TAG, "MyDatabaseHelper.update ... "  + finance.getTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FINANCE_TYPE, finance.getType());
        values.put(COLUMN_FINANCE_NOTE, finance.getTitle());
        values.put(COLUMN_FINANCE_DATE, finance.getDate());
        values.put(COLUMN_FINANCE_MONEY, finance.getMoney());

        // updating row
        return db.update(TABLE_FINANCE, values, COLUMN_FINANCE_ID + " = ?",
                new String[]{String.valueOf(finance.getId())});
    }

    public void deleteNote(ItemFinance finance) {
        Log.i(TAG, "MyDatabaseHelper.update ... " + finance.getTitle() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FINANCE, COLUMN_FINANCE_ID + " = ?",
                new String[]{String.valueOf(finance.getId())});
        db.close();
    }

    // Nếu trong bảng chưa có dữ liệu,
    // chèn vào mặc định 2 bản ghi.
    public void createDefaultToTest()  {
        int count = this.getCount();
        if(count ==0 ) {
            ItemFinance f1 = new ItemFinance("cinema","Xem phim","06/06/2016",200000);
            ItemFinance f2 = new ItemFinance("orther","Trúng số","16/06/2016",1000000);
            this.addFinance(f1);
            this.addFinance(f2);
        }
    }
}
