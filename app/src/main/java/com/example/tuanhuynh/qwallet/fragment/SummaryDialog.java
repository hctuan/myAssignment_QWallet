package com.example.tuanhuynh.qwallet.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DialogTitle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.AddNewActivity;
import com.example.tuanhuynh.qwallet.MainActivity;
import com.example.tuanhuynh.qwallet.R;
import com.example.tuanhuynh.qwallet.ReportActivity;

/**
 * Created by tuan.huynh on 6/22/2016.
 */
public class SummaryDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

//        // Close
//        dialog.findViewById(R.id.btn_close_dialog);
//        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                Toast.makeText(getContext(), "??????", Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//            }
//        });

        return dialog;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_dialog_layout,container,false);
        TextView tvIncom = (TextView)view.findViewById(R.id.tv_income);
        TextView tvExpense = (TextView)view.findViewById(R.id.tv_expense);
        TextView tvBalance = (TextView)view.findViewById(R.id.tv_balance);
        long valueIncome = getArguments().getLong("income");
        long valueExpense = getArguments().getLong("expense");
        String expense = String.valueOf(valueExpense);
        String income = String.valueOf(valueIncome);
        long valueBalance = valueIncome-valueExpense;
        tvBalance.setText(String.valueOf(valueBalance));

        tvIncom.setText(income);
        tvExpense.setText(expense);
        final String month = getArguments().getString("title");
        TextView tvTitle = (TextView)view.findViewById(R.id.tv_month);
        tvTitle.setText(getNameMonth(month));

        Button btnCloseDialog = (Button)view.findViewById(R.id.btn_close_dialog);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), ReportActivity.class);
                in.putExtra("month",month);
                in.putExtra("year",getArguments().getString("year"));
                startActivity(in);
            }
        });
        return view;
    }

    String getNameMonth(String s){
        switch (s){
            case "01": return "January";
            case "02": return "February";
            case "03": return "March";
            case "04": return "April";
            case "05": return "May";
            case "06": return "June";
            case "07": return "July";
            case "08": return "August";
            case "09": return "September";
            case "10": return "October";
            case "11": return "November";
            case "12": return "December";
            default: return "Month";
        }
    }
}
