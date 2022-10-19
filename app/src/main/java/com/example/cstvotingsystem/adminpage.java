package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adminpage extends AppCompatActivity {
    Button registerCandidate, viewCandidate ;
    private ImageSlider imageSlider;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            // Take care of calling this method on earlier versions of
            // the platform where it doesn't exist.
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        // This will be called either automatically for you on 2.0
        // or later, or by the code above on earlier versions of the
        // platform.
        startActivity(new Intent(adminpage.this, adminpage.class));

        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        imageSlider = findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.imh6, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        registerCandidate = findViewById(R.id.register_candidate);
        viewCandidate = findViewById(R.id.viewCandidates);

        viewCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewCandidates.class));

            }
        });

       registerCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register_Candidate.class));

            }
        });
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
        if(item.getItemId() == R.id.home){
            startActivity(new Intent(getApplicationContext(), adminpage.class));
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

    public void viewCandidates(View view){
      //  startActivity(new Intent(getApplicationContext(), UpdateCandidateActivity.class));
    }

    public void viewManifesto(View view) {
        startActivity(new Intent(getApplicationContext(), UpdateManifestos.class));

    }

    public void Stop(View view) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Registered User").child(fAuth.getUid());
//
//        AuthResult authResult;
//        ref.child("isVote").setValue("True");
        String uid = fAuth.getCurrentUser().getUid();
        DocumentReference df = FirebaseFirestore.getInstance().collection("StartActivity").document("id");

        Map<String, Object> map = new HashMap<>();
        map.put("isStart", "False");
        df.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(adminpage.this, "Activity Stoped", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Start(View view) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Registered User").child(fAuth.getUid());
//
//        AuthResult authResult;
//        ref.child("isVote").setValue("True");
        String uid = fAuth.getCurrentUser().getUid();
        DocumentReference ref = FirebaseFirestore.getInstance().collection("StartActivity").document("id");

        Map<String, Object> map = new HashMap<>();
        map.put("isStart", "True");
        ref.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(adminpage.this, "Activity Started", Toast.LENGTH_SHORT).show();

            }
        });
    }
}