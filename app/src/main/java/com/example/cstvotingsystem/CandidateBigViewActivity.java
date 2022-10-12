package com.example.cstvotingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CandidateBigViewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, id, email, role;
    Button MeniBtn;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_big_view);

        imageView = findViewById(R.id.image_view);
        name = findViewById(R.id.t1_view);
        id = findViewById(R.id.t2_view);
        email = findViewById(R.id.t3_view);
        role = findViewById(R.id.t4_view);

        MeniBtn = findViewById(R.id.menifestos);
        reference = FirebaseDatabase.getInstance().getReference().child("Students");

        String candidateKey = getIntent().getStringExtra("candidateKey");
        reference.child(candidateKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    String Cname = snapshot.child("Name").getValue().toString();
                    String Crole = snapshot.child("Role").getValue().toString();
                    String Cemail = snapshot.child("Email").getValue().toString();
                    String Cid = snapshot.child("Id").getValue().toString();
                    String ImageUri = snapshot.child("Image").getValue().toString();

                    Picasso.get().load(ImageUri).into(imageView);
                    name.setText(Cname);
                    id.setText(Cid);
                    email.setText(Cemail);
                    role.setText(Crole);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}