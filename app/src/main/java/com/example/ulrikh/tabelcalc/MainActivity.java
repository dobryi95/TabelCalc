package com.example.ulrikh.tabelcalc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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
    Button memoryPlus;
    Button memoryMinus;
    Button memoryRead;
    Button memoryClear;

    TextView result;

    CheckBox ndfl;

    Double memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("табель калькулятор");
        priceObnojenka = (EditText) findViewById(R.id.priceObnojenka);
        pricePortret = (EditText) findViewById(R.id.pricePortret);
        hObnojenka = (EditText) findViewById(R.id.hObnojenka);
        hPortret = (EditText) findViewById(R.id.hPortret);
        enter = (Button) findViewById(R.id.enter);
        memoryPlus = (Button) findViewById(R.id.memoryPlus);
        memoryMinus = (Button) findViewById(R.id.memoryMinus);
        memoryRead = (Button) findViewById(R.id.memoryRead);
        memoryClear = (Button) findViewById(R.id.memoryClear);
        clear = (Button) findViewById(R.id.clear);
        result = (TextView) findViewById(R.id.result);
        ndfl = (CheckBox) findViewById(R.id.ndfl);

        memory = Double.valueOf(0);


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

                result.requestFocus();
                priceObnojenka.clearFocus();
                pricePortret.clearFocus();
                hObnojenka.clearFocus();
                hPortret.clearFocus();
            }
        });

        memoryPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memory += Double.valueOf(result.getText().toString().replaceAll(",", "."));
            }
        });
        memoryMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memory -= Double.valueOf(result.getText().toString().replaceAll(",", "."));
            }
        });
        memoryRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedMemory = new DecimalFormat("#0.00").format(memory);
                result.setText(formattedMemory);
            }
        });
        memoryClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memory = Double.valueOf(0);
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result.setText(new DecimalFormat("#0.00").format(0));
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
