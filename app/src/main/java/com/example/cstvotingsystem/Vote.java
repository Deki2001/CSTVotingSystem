package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Vote extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    String Female;
    String Male;
    Button CC_btn, Female_btn, Male_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);


        CC_btn = findViewById(R.id.CCvote);
        Female_btn = findViewById(R.id.Girlsvote);
        Male_btn = findViewById(R.id.Boysvote);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Registered User");

        myRef.child("gender").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                     if(task.getResult().exists()){
                         DataSnapshot dataSnapshot = task.getResult();
                         String gender = String.valueOf(dataSnapshot.child("gender").getValue());

                         if(gender == "Female"){
                             Male_btn.setEnabled(false);

                         }

                         else{
                             Female_btn.setEnabled(false);


                         }

                     }

                }
            }
        }) ;

        // Read from the database
       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);



                    if (Objects.equals(value, Female)) {
                        Male_btn.setEnabled(false);
                    }

                    else if(Objects.equals(value, Male)){

                        Female_btn.setEnabled(false);
                    }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/


//        db = FirebaseDatabase.getInstance();
//        //root = db.getReference().child("Registered User");
//        Query query1 = FirebaseDatabase.getInstance().getReference("Registered User").child("gender");



        //Query query2 = FirebaseDatabase.getInstance().getReference("Registered User").orderByChild("gender").equalTo("MALE");

        }

    public void ccVote(View view) {
        startActivity(new Intent(getApplicationContext(), CCVote.class));

    }

    public void GCouncillor(View view) {
        startActivity(new Intent(getApplicationContext(), GirlsVote.class));

//        db = FirebaseDatabase.getInstance();
//        root = db.getReference().child("Students");

    }

    public void BCouncillor(View view) {
        startActivity(new Intent(getApplicationContext(), BoysVote.class));

    }
}