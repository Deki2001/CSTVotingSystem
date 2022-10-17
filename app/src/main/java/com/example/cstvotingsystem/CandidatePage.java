package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CandidatePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_page);
    }

    public void viewManifesto(View view) {
        startActivity(new Intent(getApplicationContext(), ManifestoRecycleview.class));

    }

    public void boycandidate(View view) {
        startActivity(new Intent(getApplicationContext(), ViewCandidates.class));

    }
}