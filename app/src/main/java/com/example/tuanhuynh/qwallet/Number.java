package com.example.tuanhuynh.qwallet;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by tuan.huynh on 6/15/2016.
 */
public class Number extends FrameLayout {
    private TextView label;
    private int num =0;

    public int getNum() {
        return num;
    }

    private void setNum(int num) {
        this.num = num;
        label.setText(num);
    }

    public Number(Context context) {
        super(context);

        label = new TextView(getContext()); // tạo thẻ cho card
        label.setTextSize(40); // size chữ số
        label.setBackgroundColor(0x0f000000);// trong suốt
        label.setGravity(Gravity.CENTER); // vị trí text ở ngay giữa

        // Tạo khoảng cách giữa các thẻ
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp); // thêm thẻ vào

        setNum(0);
    }
}
