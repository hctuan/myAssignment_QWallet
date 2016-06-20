package com.example.tuanhuynh.qwallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tuanhuynh.qwallet.adapter.CustomAdapterPasscode;

public class PasscodeLockActivity extends Activity {

    GridView grid;
    public static String[] numberList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0"};
    String pressPass = "";
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    String pass_true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode_lock);

        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        grid = (GridView) findViewById(R.id.grid_button_ontainer);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        CustomAdapterPasscode adapter = new CustomAdapterPasscode(PasscodeLockActivity.this, numberList);
        grid.setAdapter(adapter);

            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    //Toast.makeText(PasscodeLockActivity.this, "You Clicked at " + numberList[+position], Toast.LENGTH_SHORT).show();
                    //Toast.makeText(PasscodeLockActivity.this, "You Clicked at " +String.valueOf(getLengthPass()), Toast.LENGTH_SHORT).show();

                    //Toast.makeText(PasscodeLockActivity.this, "press " + pressPass, Toast.LENGTH_SHORT).show();
                    checkRadio();
                    pressPass = pressPass + String.valueOf(numberList[position]);
                    if(checkLengthPass()){
                        //Toast.makeText(PasscodeLockActivity.this, "Dài = 5", Toast.LENGTH_LONG).show();
                        if (!checkSharePreferences()) { //chưa có pass trong preference
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("pass", pressPass);
                            editor.commit();
                            Toast.makeText(PasscodeLockActivity.this, "Created new passcode", Toast.LENGTH_SHORT).show();
                            openMainActivity();
                        } else { //có pass trong shared preference
                            //pass_true = sharedpreferences.getString("pass", null); //lấy về
                            if(checkPass()){
                                openMainActivity();
                            } else {
                                Toast.makeText(PasscodeLockActivity.this, "Wrong passcode. Please enter again!" , Toast.LENGTH_SHORT).show();
                                pressPass="";
                                radioButton1.setChecked(false);
                                radioButton2.setChecked(false);
                                radioButton3.setChecked(false);
                                radioButton4.setChecked(false);
                                radioButton5.setChecked(false);
                            }
                            //Toast.makeText(PasscodeLockActivity.this, "pass đúng là:"+pass_true+" pass nhập:"+pressPass , Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        //Toast.makeText(PasscodeLockActivity.this, "Dài < 5", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }

    boolean checkSharePreferences(){
        String check = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("pass", null);
        if(check==null) return false;
        return true;
    }

    private void checkRadio() {
        switch (getLengthPass()) {
            case 0:
                radioButton1.setChecked(true);
                break;
            case 1:
                radioButton2.setChecked(true);
                break;
            case 2:
                radioButton3.setChecked(true);
                break;
            case 3:
                radioButton4.setChecked(true);
                break;
            case 4:
                radioButton5.setChecked(true);
                break;

        }
    }

    String getPressPass() {
        return pressPass;
    }

    void cleanPressPass() {
        pressPass = null;
    }

    int getLengthPass() {
        return pressPass.length();
    }
    boolean checkLengthPass(){
        if(getLengthPass()==5) return true;
        return false;
    }

    boolean checkPass(){
        pass_true = sharedpreferences.getString("pass", null);
        if(pressPass.equals(pass_true)) return true;
        return false;
    }

    public void openMainActivity()
    {
        Intent myIntent=new Intent(this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
