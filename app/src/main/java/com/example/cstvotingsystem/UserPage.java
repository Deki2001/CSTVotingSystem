package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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



}

    public void candidate(View view) {
        startActivity(new Intent(getApplicationContext(), ViewCandidates.class));

    }

    public void Result(View view) {
        startActivity(new Intent(getApplicationContext(), UserProfile.class));

    }

   /* public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        if(item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }
        if(item.getItemId() == R.id.user_profile){
            startActivity(new Intent(getApplicationContext(), UserPage.class));
        }
        if(item.getItemId() == R.id.about){
            startActivity(new Intent(getApplicationContext(), AboutPage.class));
        }
        return super.onOptionsItemSelected(item);
    }*/
}