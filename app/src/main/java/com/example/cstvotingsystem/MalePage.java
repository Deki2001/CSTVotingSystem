package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MalePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_page);
    }

    public void Voteboys(View view) {
        startActivity(new Intent(getApplicationContext(), BoysVote.class));

    }

    public void boymanifesto(View view) {
        startActivity(new Intent(getApplicationContext(), CandidatePage.class));

    }

    public void boyresult(View view) {
        startActivity(new Intent(getApplicationContext(), ViewResult.class));

    }
}