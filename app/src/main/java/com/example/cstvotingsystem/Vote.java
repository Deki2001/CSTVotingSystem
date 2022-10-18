package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Vote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
    }

    public void ccresult(View view) {
        startActivity(new Intent(getApplicationContext(), ViewResult.class));

    }

    public void femaleresult(View view) {
        startActivity(new Intent(getApplicationContext(), GirlsVote.class));

    }

    public void maleresult(View view) {
        startActivity(new Intent(getApplicationContext(), BoysVote.class));

    }
}