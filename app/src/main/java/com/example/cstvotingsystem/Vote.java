package com.example.cstvotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vote extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);


    }

    public void ccVote(View view) {
    }

    public void GCouncillor(View view) {
        startActivity(new Intent(getApplicationContext(), GirlsVote.class));

    }

    public void BCouncillor(View view) {
        startActivity(new Intent(getApplicationContext(), BoysVote.class));

    }
}