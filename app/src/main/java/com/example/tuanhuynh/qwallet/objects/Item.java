package com.example.tuanhuynh.qwallet.objects;

/**
 * Created by YobboPEL on 18/06/2016.
 */
public class Item {
    private int imageId;
    private String title;
    private String date;
    private double money;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Item(int imageId, String title, String date) {
        this.imageId = imageId;
        this.title = title;
        this.date = date;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getdate() {
        return date;
    }
    public void setdate(String date) {
        this.date = date;
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
}
