package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GirlsVote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_vote);
    }

    public void voteDCCboys(View view) {
        startActivity(new Intent(getApplicationContext(), DCCGirlsVote.class));


    }

    public void BCVote(View view) {
        startActivity(new Intent(getApplicationContext(), BlockCouncillorGirlsVote.class));


    }

    public void GCVote(View view) {
        startActivity(new Intent(getApplicationContext(), GamesCouncillorGirls.class));


    }

    public void girlsCultureVote(View view) {
        startActivity(new Intent(getApplicationContext(), CultureCouncillorGirls.class));

    }

    public void girlcacVote(View view) {
        startActivity(new Intent(getApplicationContext(), CACGirlsVote.class));

    }

    public void voteCCCboys(View view) {
        startActivity(new Intent(getApplicationContext(), CCVote.class));


    }
}