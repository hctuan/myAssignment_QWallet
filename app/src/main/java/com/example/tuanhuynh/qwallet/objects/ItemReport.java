package com.example.tuanhuynh.qwallet.objects;

/**
 * Created by tuan.huynh on 6/23/2016.
 */
public class ItemReport {
    boolean isTitle;
    String cate;
    long value;
    String note;
    String date;
    String inOrEx;

    public ItemReport(boolean isTitle, String cate, long value, String note, String date, String inOrEx) {
        this.isTitle = isTitle;
        this.cate = cate;
        this.value = value;
        this.note = note;
        this.date = date;
        this.inOrEx = inOrEx;
    }

    public ItemReport() {
    }
    public boolean isTitle() {
        return isTitle;
    }

    public void setIsTitle(boolean isTitle) {
        this.isTitle = isTitle;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInOrEx() {
        return inOrEx;
    }

    public void setInOrEx(String inOrEx) {
        this.inOrEx = inOrEx;
    }
}
