package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class BoysVote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys_vote);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void CAC(View view) {
        startActivity(new Intent(getApplicationContext(), CACBoysResult.class));

    }

    public void CultureVote(View view) {
        startActivity(new Intent(getApplicationContext(), CultureCouncillorBoysResult.class));

    }

    public void GCVote(View view) {
        startActivity(new Intent(getApplicationContext(), GamesCouncillorResult.class));

    }

    public void BlockCVote(View view) {
        startActivity(new Intent(getApplicationContext(), BlockCouncillor_boysResult.class));

    }

    public void DCCvote(View view) {
        startActivity(new Intent(getApplicationContext(), DDC_boysResult.class));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }
        if(item.getItemId() == R.id.user_profile){
            startActivity(new Intent(getApplicationContext(), UserProfile.class));
        }
        if(item.getItemId() == R.id.about){
            startActivity(new Intent(getApplicationContext(), AboutPage.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerCandidates(View view){
        Toast.makeText(this, "Register Candidates", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Register_Candidate.class));
    }


    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}