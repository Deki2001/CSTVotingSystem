package com.example.cstvotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register_Candidate extends AppCompatActivity {
    Button viewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_candidate);

        viewList = findViewById(R.id.show_list);

    }

    public void viewList(View view) {
        startActivity(new Intent(getApplicationContext(), ViewCandidates.class));

    }
}