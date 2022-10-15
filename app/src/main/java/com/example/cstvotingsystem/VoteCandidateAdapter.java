package com.example.cstvotingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VoteCandidateAdapter extends RecyclerView.Adapter<VoteCandidateAdapter.ViewHolder> {

    FirebaseDatabase database;
    DatabaseReference reference;



    Context context;
    List<CandidateModel> candidateModelList;
   // FirebaseFirestore fStore = FirebaseFirestore.getInstance();
  //  FieldValue increment = FieldValue.increment(1);


    public VoteCandidateAdapter(Context context, List<CandidateModel> candidateModelList) {
        this.context = context;
        this.candidateModelList = candidateModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vote_list,parent,false);
        return new VoteCandidateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CandidateModel candidateModel = candidateModelList.get(position);

        holder.name.setText("Name: " + candidateModel.getName());
       // holder.email.setText("Email: " + candidateModel.getEmail());
        holder.id.setText("ID: " + candidateModel.getId());
       // holder.role.setText("Role: " + candidateModel.getRole());

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


       // Query query = FirebaseDatabase.getInstance().getReference("Students").orderByChild("Role").equalTo("Chief Councillor");

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_voteView);
            name = itemView.findViewById(R.id.t1);
            id = itemView.findViewById(R.id.t4);
            vote = itemView.findViewById(R.id.voteButton);
            //For big candidate view
          //  v= itemView;

         /*   vote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   database = FirebaseDatabase.getInstance();
                   reference = database.getReference();
            });
            vote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("TAG", "onClick: Voted for Candidate " + CurrentVote.Name);
                    documentReference = fStore.collection(CurrentVote.Role).document(CurrentVote.getId());
                    documentReference.update("Vote", increment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("TAG", "onComplete: Vote Success");
                            Toast.makeText(inflater.getContext(), "Vote Successfully Counted.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onFailure: Error!" + e.toString());
                            Toast.makeText(inflater.getContext(), "Error !" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });*/

        }
    }

}
