package com.example.cstvotingsystem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VoteCandidateAdapter extends RecyclerView.Adapter<VoteCandidateAdapter.ViewHolder> {

    Context context;
    List<CandidateModel> candidateModelList;

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
        View v;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_voteView);
            name = itemView.findViewById(R.id.t1);
            id = itemView.findViewById(R.id.t4);

            //For big candidate view

          //  v= itemView;

        }
    }

}
