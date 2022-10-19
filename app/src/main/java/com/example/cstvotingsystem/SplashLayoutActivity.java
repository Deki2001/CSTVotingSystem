package com.example.cstvotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashLayoutActivity extends AppCompatActivity {

    private static int Splash_timeout=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent = new Intent(SplashLayoutActivity.this, Login.class);
                startActivity(splashintent);
                finish();
            }
        }, Splash_timeout);

    }
    }