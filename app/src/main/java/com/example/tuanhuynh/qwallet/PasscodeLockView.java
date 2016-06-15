package com.example.tuanhuynh.qwallet;

import android.content.Context;
import android.widget.GridLayout;

/**
 * Created by tuan.huynh on 6/15/2016.
 */
public class PasscodeLockView extends GridLayout {
    public PasscodeLockView(Context context) {
        super(context);
        initPasscodeLockView();
    }

    private Number[][] numberMap = new Number[4][3];

    private void initPasscodeLockView(){
        setColumnCount(3);
        setRowCount(4);
        setBackgroundColor(0xffCCFF66);
    }


}
