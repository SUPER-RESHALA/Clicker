package com.example.clicker3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Improve extends AppCompatActivity {
    Button buy1;
    Button back;
    @SuppressLint("StaticFieldLeak")
    public static TextView price;
    static float setPrice = 10;
    MainActivity mainActivity = new MainActivity();
    SharedPreferences preferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve);

        buy1 = (Button) findViewById(R.id.buy1);
        price = (TextView) findViewById(R.id.price);
        price.setText("Цена: "+setPrice + "");
        back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Improve.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.countMoney >= setPrice) {
                    MainActivity.countMoney -= setPrice;
                    MainActivity.money.setText(MainActivity.countMoney + "");
                    MainActivity.moneyPerClick += 0.5;
                    setPrice += 50;
                    price.setText("Цена: "+setPrice + "");
                    new Saved().save();


                }
            }
        });

    }
}
