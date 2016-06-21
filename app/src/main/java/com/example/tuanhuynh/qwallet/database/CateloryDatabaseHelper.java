package com.example.tuanhuynh.qwallet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tuanhuynh.qwallet.objects.Catelories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuan.huynh on 6/21/2016.
 */
public class CateloryDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "CateloryData";


    // Tên bảng: Note.
    private static final String TABLE_NAME = "CateloryTable";

    private static final String COLUMN_ID ="Catelory_Id";
    private static final String COLUMN_Name ="Catelory_Name";
    private static final String COLUMN_Icon = "Catelory_Icon";

    public CateloryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_Name + " TEXT,"
                + COLUMN_Icon + " TEXT" + ")";
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Và tạo lại.
        onCreate(db);
    }

    public void addCatelory(Catelories catelories) {
        Log.i(TAG, "MyDatabaseHelper.addFinance ... " + catelories.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Icon, catelories.getName());
        values.put(COLUMN_Name, catelories.getIconName());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_NAME, null, values);


        // Đóng kết nối database.
        db.close();
    }
    public List<Catelories> getAll() {
        Log.i(TAG, "MyDatabaseHelper.getAll ... " );

        List<Catelories> list = new ArrayList<Catelories>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Catelories catelories = new Catelories();
                catelories.setId(Integer.parseInt(cursor.getString(0)));
                catelories.setIconName(cursor.getString(1));
                catelories.setName(cursor.getString(2));

                // Thêm vào danh sách.
                list.add(catelories);
            } while (cursor.moveToNext());
        }

        // return note list
        return list;
    }

    public int getCount() {
        Log.i(TAG, "MyDatabaseHelper.getCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    // Nếu trong bảng chưa có dữ liệu,
    // chèn vào mặc định 2 bản ghi.
    public void createDefaultToTest()  {
        int count = this.getCount();
        if(count ==0 ) {
            Catelories f1 = new Catelories(1,"cinema","cinema");
            Catelories f2 = new Catelories(2,"other","other");
            Catelories f3 = new Catelories(3,"party","party");
            Catelories f4 = new Catelories(4,"salary","salary");
            this.addCatelory(f1);
            this.addCatelory(f2);
            this.addCatelory(f3);
            this.addCatelory(f4);
        }
    }
}
