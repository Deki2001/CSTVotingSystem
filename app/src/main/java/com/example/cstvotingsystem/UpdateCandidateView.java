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
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class  UpdateCandidateView extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    FirebaseStorage mStroage;
    RecyclerView recyclerView;
    UpdateCandidateAdapter candidateAdapter;
    List<CandidateModel> candidateMdList;


    //RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_candidate_view);

        recyclerView = (RecyclerView)findViewById(R.id.updateCandidaterecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseDatabase.getInstance();
        root = db.getReference().child("Students");

        // Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("Role").equalTo("Chief councillor");

//        FirebaseRecyclerOptions<CandidateModel> options =
//          new FirebaseRecyclerOptions.Builder<>()
//                  .setQuery(FirebaseDatabase.getInstance().getReference().child("Student"), CandidateModel.class)
//                  .build();

        mStroage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.updateCandidaterecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        candidateMdList = new ArrayList<CandidateModel>();
        candidateAdapter = new UpdateCandidateAdapter(UpdateCandidateView.this, candidateMdList);
        recyclerView.setAdapter(candidateAdapter);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                CandidateModel candidateModel = snapshot.getValue(CandidateModel.class);
                candidateMdList.add(candidateModel);
                candidateAdapter.notifyDataSetChanged();
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


