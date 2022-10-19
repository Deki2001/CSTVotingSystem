package com.example.cstvotingsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VoteCandidateAdapter extends RecyclerView.Adapter<VoteCandidateAdapter.ViewHolder> {

    FirebaseDatabase database;
    DatabaseReference reference;
    String vote_ID;
    String current_key;



    Context context;
    List<CandidateModel> candidateModelList;
    private boolean clicked = false;
    // FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    //  FieldValue increment = FieldValue.increment(1);


    public VoteCandidateAdapter(Context context, List<CandidateModel> candidateModelList) {
        this.context = context;
        this.candidateModelList = candidateModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        View v = inflater.from(parent.getContext()).inflate(R.layout.activity_vote_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);


        return new VoteCandidateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CandidateModel candidateModel = candidateModelList.get(position);

        final ViewHolder myHolder = (ViewHolder) holder;
        if (clicked) myHolder.vote.setEnabled(false);
        else myHolder.vote.setEnabled(true);



        holder.name.setText("Name: " + candidateModel.getName());
        // holder.email.setText("Email: " + candidateModel.getEmail());
        holder.id.setText("ID: " + candidateModel.getId());
        // holder.role.setText("Role: " + candidateModel.getRole());
        final int value = getItemCount();

        String image = null;
        image = candidateModel.getImage();
        Picasso.get().load(image).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return candidateModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView id, name;
        Button vote;
        CandidateModel CurrentVote;
        LayoutInflater inflater;
        DocumentReference documentReference;
        // FirebaseUser user;





        // Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("Role").equalTo("Chief Councillor");

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.image_voteView);
            name = itemView.findViewById(R.id.t1);
            id = itemView.findViewById(R.id.t4);
            vote = itemView.findViewById(R.id.voteButton);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userId = user.getUid();
            DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
            int i=0;

            //For big candidate view
            //  v= itemView;

          /*  vote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatabaseReference assetsRef = mDatabaseReference.child("Students");
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String key = ds.getKey();
                                current_key = key;
                                System.out.println(key);
                                System.out.println(vote_ID);
                                Log.d(TAG, key);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
                        }
                    };

                    assetsRef.addListenerForSingleValueEvent(valueEventListener);

                    String text[]=id.getText().toString().split(":",2);
                    System.out.println("..................."+text[1]);
                    String str=text[1].trim();
                    mDatabaseReference.child("Students").child(str).child("Vote").setValue(ServerValue.increment(1));
                }
            });*/ // main Function of vote





            vote.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    clicked = true;
                    notifyDataSetChanged();
                    //start alert dialog
                    final AlertDialog.Builder Dialog = new AlertDialog.Builder(v.getContext());
                    Dialog.setTitle("Are you sure to vote? ");


                    Dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String userId = user.getUid();
                            DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
                            int i=0;
                            DatabaseReference assetsRef = mDatabaseReference.child("Students");
                            ValueEventListener valueEventListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        String key = ds.getKey();
                                        current_key = key;
                                       // System.out.println(key);
                                       // System.out.println(vote_ID);
                                       // Log.d(TAG, key);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                   // Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
                                }
                            };

                            assetsRef.addListenerForSingleValueEvent(valueEventListener);

                            String text[]=id.getText().toString().split(":",2);
                            System.out.println("..................."+text[1]);
                            String str=text[1].trim();
                            mDatabaseReference.child("Students").child(str).child("Vote").setValue(ServerValue.increment(1));
                            DatabaseReference current = mDatabaseReference.child("Students").child(str).child("Role");



                            Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("Role");
                            query.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot datasnapshot, @Nullable String previousChildName) {
//                                    for (DataSnapshot ds : datasnapshot.getChildren()) {
//                                        ViewHolder viewHolder = new ViewHolder(itemView);
//                                        viewHolder.vote.setEnabled(false);
//
//                                        if (clicked) vi
//                                        else viewHolder.vote.setEnabled(true);
//                                    }



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

                    });



                    Dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // close the dialog


                        }
                    });

                    Dialog.create().show();
                }
            });






        }
    }


}