package com.example.ulrikh.tabelcalc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    EditText priceObnojenka, pricePortret, hObnojenka, hPortret;
    Button enter, clear, memoryPlus, memoryMinus, memoryRead, memoryClear, settings, settingsSave;
    TextView result;
    CheckBox ndfl;

    Float memory;
    Boolean settingsOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("ТАБЕЛЬ КАЛЬКУЛЯТОР");

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
        settings = (Button) findViewById(R.id.settings);
        settingsSave = (Button) findViewById(R.id.settingsSave);

        result.setText(new DecimalFormat("#0.00").format(0));

        memory = Float.valueOf(LoadPreferences("APP_PREFERENCES_MEMORY"));

        if(LoadPreferences("APP_PREFERENCES_PRICEO")!="0") {
            priceObnojenka.setText(LoadPreferences("APP_PREFERENCES_PRICEO"));
        }else{
            priceObnojenka.setText("");
        }
        if(LoadPreferences("APP_PREFERENCES_PRICEP")!="0") {
            pricePortret.setText(LoadPreferences("APP_PREFERENCES_PRICEP"));
        }else{
            pricePortret.setText("");
        }

        if(LoadPreferences("APP_PREFERENCES_NDFL")=="true") {
            ndfl.setChecked(true);
            }


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
                Float pO = Float.valueOf(priceObnojenka.getText().toString());
                Float pP = Float.valueOf(pricePortret.getText().toString());
                Float hO = Float.valueOf(hObnojenka.getText().toString());
                Float hP = Float.valueOf(hPortret.getText().toString());
                Float floatResult = (pO * hO) + (pP * hP);

                if (ndfl.isChecked()){
                    floatResult = floatResult - (floatResult/100*13);
                }
                String formattedResult = new DecimalFormat("#0.00").format(floatResult);
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
                memory += Float.valueOf(result.getText().toString().replaceAll(",", "."));

                SavePreferences("APP_PREFERENCES_MEMORY", String.valueOf(memory));

            }
        });
        memoryMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memory -= Float.valueOf(result.getText().toString().replaceAll(",", "."));

                SavePreferences("APP_PREFERENCES_MEMORY", String.valueOf(memory));

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
                memory = 0f;

                SavePreferences("APP_PREFERENCES_MEMORY", String.valueOf(memory));

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result.setText(new DecimalFormat("#0.00").format(0));
                hObnojenka.setText("");
                hPortret.setText("");

                priceObnojenka.clearFocus();
                pricePortret.clearFocus();
                hObnojenka.clearFocus();
                hPortret.clearFocus();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((settingsOpen != true)) {
                    priceObnojenka.setVisibility(View.VISIBLE);
                    pricePortret.setVisibility(View.VISIBLE);
                    ndfl.setVisibility(View.VISIBLE);
                    settingsSave.setVisibility(View.VISIBLE);
                    settingsOpen=true;

                }else{
                    priceObnojenka.setVisibility(View.INVISIBLE);
                    pricePortret.setVisibility(View.INVISIBLE);
                    ndfl.setVisibility(View.INVISIBLE);
                    settingsSave.setVisibility(View.INVISIBLE);
                    settingsOpen=false;
                }
            }
        });

        settingsSave.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SavePreferences("APP_PREFERENCES_PRICEO", priceObnojenka.getText().toString());
                SavePreferences("APP_PREFERENCES_PRICEP", pricePortret.getText().toString());
                if (ndfl.isChecked()){
                    SavePreferences("APP_PREFERENCES_NDFL", String.valueOf("true"));
                }else{
                    SavePreferences("APP_PREFERENCES_NDFL", String.valueOf("false"));
                }
            }
        }));

    }

    private void SavePreferences(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String LoadPreferences(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(key, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(key)) {
            String string = sharedPreferences.getString(key, "");
            return string;
        }
        return "0";
    }
}
