package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BoysVote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys_vote);
    }

    public void CAC(View view) {
        startActivity(new Intent(getApplicationContext(), CACBoysVote.class));

    }

    public void CultureVote(View view) {
        startActivity(new Intent(getApplicationContext(), CultureCouncillorBoys.class));

    }

    public void GCVote(View view) {
        startActivity(new Intent(getApplicationContext(), GamesCouncillorVote.class));

    }

    public void BlockCVote(View view) {
        startActivity(new Intent(getApplicationContext(), BlockCouncillor_boys.class));

    }

    public void DCCvote(View view) {
        startActivity(new Intent(getApplicationContext(), DCC_Boys.class));

    }
}