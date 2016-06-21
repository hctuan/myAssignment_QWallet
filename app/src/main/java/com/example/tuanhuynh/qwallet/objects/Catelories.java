package com.example.tuanhuynh.qwallet.objects;

/**
 * Created by tuan.huynh on 6/21/2016.
 */
public class Catelories {
    int id;
    String name;
    String iconName;

    public Catelories() {
    }

    public Catelories(String name, String iconName) {
        this.name = name;
        this.iconName = iconName;
    }

    public Catelories(int id, String name, String iconName) {
        this.id = id;
        this.name = name;
        this.iconName = iconName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
