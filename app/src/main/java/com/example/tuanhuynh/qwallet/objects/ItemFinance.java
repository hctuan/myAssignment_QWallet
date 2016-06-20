package com.example.tuanhuynh.qwallet.objects;

import com.example.tuanhuynh.qwallet.R;

/**
 * Created by YobboPEL on 18/06/2016.
 */
public class ItemFinance {
    private int id;
    private String type;
    private String title;
    private String date;
    private double money;

    public ItemFinance() {
    }
//    public ItemFinance(String type, String title, String date) {
//        this.type = type;
//        this.title = title;
//        this.date = date;
//    }
    public ItemFinance(String type, String title, String date, double money) {
        this.type = type;
        this.title = title;
        this.date = date;
        this.money = money;
    }
    public ItemFinance(int id, String type, String title, String date, double money) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.date = date;
        this.money = money;
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
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
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
    @Override
    public String toString() {
        return title + "\n" + date;
    }

//    private int getImageId(String type){
//        switch(type){
//            case "shop":
//                return R.drawable.edit;
//            case "cinema":
//                return R.drawable.edit;
//            case "salary":
//                return R.drawable.edit;
//            case "wedding":
//                return R.drawable.edit;
//            default:
//                return R.drawable.edit;
//        }
//    }
}
