package com.example.cstvotingsystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class CultureCouncillorGirls extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_councillor_girls);


        FirebaseDatabase db;
        DatabaseReference root;
        FirebaseStorage mStroage;
        RecyclerView recyclerView;
        VoteCandidateAdapter Adapter;
        List<CandidateModel> candidateMdList;


        recyclerView = (RecyclerView)findViewById(R.id.CultureCouncillorGirlsrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseDatabase.getInstance();
        //root = db.getReference().child("Students");

        Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("Role").equalTo("Girls Cultural Councillor");

//        FirebaseRecyclerOptions<CandidateModel> options =
//          new FirebaseRecyclerOptions.Builder<>()
//                  .setQuery(FirebaseDatabase.getInstance().getReference().child("Student"), CandidateModel.class)
//                  .build();

        mStroage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.CultureCouncillorGirlsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        candidateMdList = new ArrayList<CandidateModel>();
        Adapter = new VoteCandidateAdapter(CultureCouncillorGirls.this, candidateMdList);
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
}


