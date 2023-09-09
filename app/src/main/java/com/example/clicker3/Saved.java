package com.example.clicker3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ConcurrentModificationException;

public class Saved extends AppCompatActivity {
    private static SharedPreferences.Editor editor;
    private static SharedPreferences preferences;

    public Saved() {
    }

    public static void init(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = preferences.edit();
        }
    }

    void save() {
        editor.putFloat("money", MainActivity.countMoney);
        editor.putFloat("setPrice", Improve.setPrice);
        editor.putFloat("moneyPerClick", MainActivity.moneyPerClick);
        editor.putInt("count", MainActivity.count);
        editor.putFloat("autoClickPrice", Improve.setAutoclickPrice);
        editor.putFloat("moneyPerAutoclick", MainActivity.moneyPerAutoClick);
        editor.putBoolean("autoclickFlag", MainActivity.autoclickFlag);
        editor.commit();
    }

    @SuppressLint("SetTextI18n")
    void load_save() {
        MainActivity.countMoney = preferences.getFloat("money", 0);
        MainActivity.money.setText(MainActivity.countMoney + "");
        MainActivity.moneyPerClick = preferences.getFloat("moneyPerClick", 0.5F);
        Improve.setPrice = preferences.getFloat("setPrice", 10);
        MainActivity.count = preferences.getInt("count", 0);
        MainActivity.counter.setText("Всего кликнуто: " + MainActivity.count);
        Improve.setAutoclickPrice = preferences.getFloat("autoClickPrice", Improve.setAutoclickPrice);
        MainActivity.moneyPerAutoClick = preferences.getFloat("moneyPerAutoclick", MainActivity.moneyPerAutoClick);
        MainActivity.autoclickFlag = preferences.getBoolean("autoclickFlag", false);
        //System.out.println(MainActivity.autoclickFlag+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //Improve.price.setText("Цена: "+Improve.setPrice);
    }
}
