package com.example.clicker3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Improve extends AppCompatActivity {
    Button buy1;
    Button back;
    Button buyAutoclick;
    @SuppressLint("StaticFieldLeak")
    public static TextView price;
    public static TextView autoclickPrice;
    static float setPrice = 10;
    static float setAutoclickPrice = 100;
    //MainActivity mainActivity = new MainActivity();
    SharedPreferences preferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve);

        buy1 = (Button) findViewById(R.id.buy1);
        price = (TextView) findViewById(R.id.price);
        price.setText("Цена: " + setPrice + "");
        back = (Button) findViewById(R.id.back);
        autoclickPrice = (TextView) findViewById(R.id.AutoclickPrice);
        autoclickPrice.setText("Цена: " + setAutoclickPrice + "");
        buyAutoclick = (Button) findViewById(R.id.BuyAutoclickButton);


        buyAutoclick.setOnClickListener((new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (MainActivity.countMoney >= setAutoclickPrice) {
                    MainActivity.autoclickFlag = true;

                    //new Saved().save();
                    MainActivity.countMoney -= setAutoclickPrice;      //Забираем деньги за улучшение
                    Improve.setAutoclickPrice +=10;//Увеличиваем цену дальнешего улучшения покупки автоклика
                    Improve.autoclickPrice.setText("Цена: " + setAutoclickPrice + "");//Отображаем новую цену
                    MainActivity.moneyPerAutoClick += 1F;
                    AutoMoney();
                    //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }

            }

        }));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Improve.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buy1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (MainActivity.countMoney >= setPrice) {

                    MainActivity.countMoney -= setPrice;
                    MainActivity.money.setText(MainActivity.countMoney + "");
                    MainActivity.moneyPerClick += 1000;
                    setPrice += 50;
                    price.setText("Цена: " + setPrice + "");
                    new Saved().save();


                }
            }
        });

    }

    public void AutoMoney() {
        Timer timer1 = new Timer();
        //здесь мы добавляем таймер и вызываем метод shedule, TimerTask оказывает, что надо выполнять в определенный период
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.countMoney += MainActivity.moneyPerAutoClick;//это увеличение
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        MainActivity.money.setText(MainActivity.countMoney + "");
                        new Saved().save();
                    }
                });
                // new Saved().save();
            }
        }, 0, 3000);
    }
}
