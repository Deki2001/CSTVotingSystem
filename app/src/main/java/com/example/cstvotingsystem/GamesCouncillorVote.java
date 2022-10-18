package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamesCouncillorVote extends AppCompatActivity {
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
        startActivity(new Intent(GamesCouncillorVote.this, GamesCouncillorVote.class));

        return;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_councillor_vote);


        FirebaseDatabase db;
        DatabaseReference root;
        FirebaseStorage mStroage;
        RecyclerView recyclerView;
        VoteCandidateAdapter Adapter;
        List<CandidateModel> candidateMdList;


        recyclerView = (RecyclerView)findViewById(R.id.BoyGamesCouncillorRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseDatabase.getInstance();
        //root = db.getReference().child("Students");

        Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("Role").equalTo("Boys Game Councillor");

//        FirebaseRecyclerOptions<CandidateModel> options =
//          new FirebaseRecyclerOptions.Builder<>()
//                  .setQuery(FirebaseDatabase.getInstance().getReference().child("Student"), CandidateModel.class)
//                  .build();

        mStroage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.BoyGamesCouncillorRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        candidateMdList = new ArrayList<CandidateModel>();
        Adapter = new VoteCandidateAdapter(GamesCouncillorVote.this, candidateMdList);
        recyclerView.setAdapter(Adapter);






        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                CandidateModel candidateModel = snapshot.getValue(CandidateModel.class);
                candidateMdList.add(candidateModel);
                Adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void submit(View view) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Registered User").child(fAuth.getUid());
//
//        AuthResult authResult;
//        ref.child("isVote").setValue("True");
        String uid = fAuth.getCurrentUser().getUid();
        DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(uid);

        Map<String, Object> map = new HashMap<>();
        map.put("isVote", "True");
        df.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                startActivity(new Intent(getApplicationContext(), MalePage.class));

            }
        });
    }
}


