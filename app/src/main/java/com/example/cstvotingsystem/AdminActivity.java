package com.example.cstvotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button vcandidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        vcandidate = findViewById(R.id.CCvote);


    }

    public void viewCandidates(View view) {
    }


    public void registerCandidates(View view) {
    }
}