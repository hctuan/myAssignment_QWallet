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
        //String month = getArguments().getString("month");
        getDialog().setTitle("Summary for " + income + "  " + expense);

        Button btnCloseDialog = (Button)view.findViewById(R.id.btn_close_dialog);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openEditActivity();
                //dismiss();
            }
        });
        return view;
    }
}
