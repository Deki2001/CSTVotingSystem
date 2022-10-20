package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class  ManifestoRecycleview extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    ManifestoAdapter manifestoAdapter;
    List<ManifestoModel> manifestoModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifesto_recycleview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView = (RecyclerView)findViewById(R.id.manifestRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Manifestos");
        mStorage = FirebaseStorage.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        manifestoModelList = new ArrayList<ManifestoModel>();
        manifestoAdapter = new ManifestoAdapter(ManifestoRecycleview.this, manifestoModelList);
        recyclerView.setAdapter(manifestoAdapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ManifestoModel manifestoModel = dataSnapshot.getValue(ManifestoModel.class);
                manifestoModelList.add(manifestoModel);
                manifestoAdapter.notifyDataSetChanged();
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
        if (item.getItemId() == R.id.home) {
            startActivity(new Intent(getApplicationContext(), UserPage.class));
        }
        return super.onOptionsItemSelected(item);
    }
}