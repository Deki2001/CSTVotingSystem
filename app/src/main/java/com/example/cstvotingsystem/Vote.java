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


    public void ccVote(View view) {
        startActivity(new Intent(getApplicationContext(), CCVote.class));

    }

    public void GCouncillor(View view) {
        startActivity(new Intent(getApplicationContext(), GirlsVote.class));

    }

    public void BCouncillor(View view) {
        startActivity(new Intent(getApplicationContext(), BoysVote.class));

    }
}