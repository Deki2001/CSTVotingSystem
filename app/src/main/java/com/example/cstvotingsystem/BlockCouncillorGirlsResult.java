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

public class BlockCouncillorGirlsResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_councillor_girls_result);


        FirebaseDatabase db;
        DatabaseReference root;
        FirebaseStorage mStroage;
        RecyclerView recyclerView;
        ViewResultAdapter Adapter;
        List<ViewResultModel> candidateMdList;



        recyclerView = (RecyclerView)findViewById(R.id.GirlsBlockCouncillor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseDatabase.getInstance();
        root = db.getReference().child("Students");

        Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("Role").equalTo("Girls Block Councillor");

//        FirebaseRecyclerOptions<CandidateModel> options =
//          new FirebaseRecyclerOptions.Builder<>()
//                  .setQuery(FirebaseDatabase.getInstance().getReference().child("Student"), CandidateModel.class)
//                  .build();

        mStroage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.GirlsBlockCouncillor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        candidateMdList = new ArrayList<ViewResultModel>();
        Adapter = new ViewResultAdapter(BlockCouncillorGirlsResult.this,candidateMdList);
        recyclerView.setAdapter(Adapter);


        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                ViewResultModel candidateModel = snapshot.getValue(ViewResultModel.class);
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