package com.example.test_library;

import android.os.Bundle;

import com.android.reportx.util.RP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RP.debug = true;
        //上报激活
        Button activeBtn = findViewById(R.id.reportActive);
        activeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RP.launch(MainActivity.this);
            }
        });
        //上报注册
        Button regBtn = findViewById(R.id.reportRegister);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RP.debug = true;
                RP.reg(MainActivity.this);
            }
        });
        //上报付费
        Button payBtn = findViewById(R.id.reportPay);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RP.debug = true;
                RP.pay(MainActivity.this,10);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}