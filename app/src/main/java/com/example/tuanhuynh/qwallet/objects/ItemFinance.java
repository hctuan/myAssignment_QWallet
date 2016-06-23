package com.example.tuanhuynh.qwallet.objects;

import com.example.tuanhuynh.qwallet.R;

import java.io.Serializable;

/**
 * Created by YobboPEL on 18/06/2016.
 */
public class ItemFinance implements Serializable {
    private int id;
    private String type;
    private String title;
    private String date;
    private long money;
    private int categoryID;

    public ItemFinance() {
    }

    public ItemFinance(int id, String type, String title, String date, long money, int categoryID) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.date = date;
        this.money = money;
        this.categoryID = categoryID;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public long getMoney() {
        return money;
    }
    public void setMoney(long money) {
        this.money = money;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return title + "\n" + date;
    }
}
