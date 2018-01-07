package com.example.ulrikh.tabelcalc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Inet4Address;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText priceObnojenka;
    EditText pricePortret;
    EditText hObnojenka;
    EditText hPortret;

    Button enter;
    Button clear;

    TextView result;

    CheckBox ndfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceObnojenka = (EditText) findViewById(R.id.priceObnojenka);
        pricePortret = (EditText) findViewById(R.id.pricePortret);
        hObnojenka = (EditText) findViewById(R.id.hObnojenka);
        hPortret = (EditText) findViewById(R.id.hPortret);
        enter = (Button) findViewById(R.id.enter);
        clear = (Button) findViewById(R.id.clear);
        result = (TextView) findViewById(R.id.result);
        ndfl = (CheckBox) findViewById(R.id.ndfl);


        priceObnojenka.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        pricePortret.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        hObnojenka.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        hPortret.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (priceObnojenka.getText().toString().equalsIgnoreCase("")){
                    priceObnojenka.setText("0");
                }
                if (pricePortret.getText().toString().equalsIgnoreCase("")){
                    pricePortret.setText("0");
                }
                if (hObnojenka.getText().toString().equalsIgnoreCase("")){
                    hObnojenka.setText("0");
                }
                if (hPortret.getText().toString().equalsIgnoreCase("")){
                    hPortret.setText("0");
                }
                double pO = Double.valueOf(priceObnojenka.getText().toString());
                double pP = Double.valueOf(pricePortret.getText().toString());
                double hO = Double.valueOf(hObnojenka.getText().toString());
                double hP = Double.valueOf(hPortret.getText().toString());
                double doubleResult = (pO * hO) + (pP * hP);

                if (ndfl.isChecked()){
                    doubleResult = doubleResult - (doubleResult/100*13);
                }
                String formattedResult = new DecimalFormat("#0.00").format(doubleResult);
                result.setText(formattedResult);

                priceObnojenka.clearFocus();
                pricePortret.clearFocus();
                hObnojenka.clearFocus();
                hPortret.clearFocus();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result.setText("0");
                priceObnojenka.setText("");
                pricePortret.setText("");
                hObnojenka.setText("");
                hPortret.setText("");

                priceObnojenka.clearFocus();
                pricePortret.clearFocus();
                hObnojenka.clearFocus();
                hPortret.clearFocus();
            }
        });

    }


}
