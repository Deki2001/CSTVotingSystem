package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class GirlsVote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_vote);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }
        if (item.getItemId() == R.id.user_profile) {
            startActivity(new Intent(getApplicationContext(), UserProfile.class));
        }
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(getApplicationContext(), AboutPage.class));
        }
        if (item.getItemId() == R.id.home){
            startActivity(new Intent(getApplicationContext(), UserPage.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void voteDCCboys(View view) {
        startActivity(new Intent(getApplicationContext(),DDC_girlsResult.class));


    }

    public void BCVote(View view) {
        startActivity(new Intent(getApplicationContext(), BlockCouncillorGirlsResult.class));


    }

    public void GCVote(View view) {
        startActivity(new Intent(getApplicationContext(), GamesCouncillorGirlsResult.class));


    }

    public void girlsCultureVote(View view) {
        startActivity(new Intent(getApplicationContext(), CultureCouncillorGirlsResult.class));

    }

    public void girlcacVote(View view) {
        startActivity(new Intent(getApplicationContext(), CACGirlsResult.class));

    }

}