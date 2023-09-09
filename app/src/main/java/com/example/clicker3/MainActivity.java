package com.example.clicker3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.*;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static boolean autoclickFlag = false;
    static public float countMoney = 0;
    static public float moneyPerClick = 0.5F;
    static public float moneyPerAutoClick = 0F;
    //  static public float improveAutoclick=1F;// пока что не удаляй
    Button click_;
    Button improve;
    static public int count = 0;
    @SuppressLint("StaticFieldLeak")
    public static TextView money;

    static TextView counter;
    SharedPreferences preferences;
    Improve imp = new Improve();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click_ = (Button) findViewById(R.id.click_);
        money = (TextView) findViewById(R.id.money);
        counter = (TextView) findViewById(R.id.counter);
        improve = (Button) findViewById(R.id.improve);
        Click_();
        Saved.init(getApplicationContext());
        Improve();
        new Saved().load_save();
        if (autoclickFlag) {
            imp.AutoMoney();
        }


    }

    void Click_() {
        click_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countMoney += moneyPerClick;
                money.setText(countMoney + "");
                count++;
                counter.setText("Всего кликнуто: " + count);
                new Saved().save();
            }
        });

    }

    void Improve() {
        improve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Improve.class);
                startActivity(intent);
            }
        });
    }


    void save() {
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("money", countMoney);
        editor.commit();
    }

    void load_save() {
        preferences = getPreferences(MODE_PRIVATE);
        countMoney = preferences.getFloat("money", 0);
        money.setText(countMoney + "");
    }
}