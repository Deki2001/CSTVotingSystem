package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UserPage extends AppCompatActivity{


    Button voter;
    private ImageSlider imageSlider;
    FirebaseFirestore fStore;

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
        startActivity(new Intent(UserPage.this, UserPage.class));

        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        fStore = FirebaseFirestore.getInstance();
        imageSlider = findViewById(R.id.imageSlider);


        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.imh6, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        voter = findViewById(R.id.Boyvote);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        String uid = fAuth.getCurrentUser().getUid();


        DocumentReference df = fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.getString("isVote").equals("True")) {
                    //user is admin

                    voter.setEnabled(false);
                }
            }
        });



//        Query query = FirebaseDatabase.getInstance().getReference("Registered User").orderByChild("isVote");
//        query.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot datasnapshot, @Nullable String previousChildName) {
//                String va = datasnapshot.getValue().toString();
//                if(va.equals("True")){
//                    vote.setEnabled(false);
//                }
//                else vote.setEnabled(true);
//
//
//
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//





    }

    public void candidate (View view){
        startActivity(new Intent(getApplicationContext(), ManifestoRecycleview .class));

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
        return super.onOptionsItemSelected(item);
    }
    public void logout_user (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }


    public void boymanifesto(View view) {
        startActivity(new Intent(getApplicationContext(), CandidatePage.class));

    }

    public void Voteboys(View view) {
        startActivity(new Intent(getApplicationContext(), CCVote.class));

    }

    public void boyresult(View view) {
        startActivity(new Intent(getApplicationContext(), GirlsVote.class));

    }
}