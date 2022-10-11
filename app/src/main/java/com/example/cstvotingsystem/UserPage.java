package com.example.cstvotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserPage extends AppCompatActivity {

    Button vote, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        vote = findViewById(R.id.vote);
        result = findViewById(R.id.result);

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Vote.class));

            }
        });


    result.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), AboutPage.class));

        }
    });
}

    public void candidate(View view) {
        startActivity(new Intent(getApplicationContext(), ViewCandidates.class));

    }
}