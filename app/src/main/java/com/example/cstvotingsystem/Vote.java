package com.example.cstvotingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

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