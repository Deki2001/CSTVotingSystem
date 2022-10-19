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

import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpdateCandidateAdapter extends RecyclerView.Adapter<UpdateCandidateAdapter.ViewHolder> {

    Context context;
    List<CandidateModel> UpdateModelList;


    public UpdateCandidateAdapter(Context context, List<CandidateModel> candidateModelList) {
        this.context = context;
        this.UpdateModelList = candidateModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_candidate_one,parent,false);

        return new UpdateCandidateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CandidateModel updateCandidateModel = UpdateModelList.get(position);
        holder.name.setText("Name: " + updateCandidateModel.getName());
        holder.email.setText("Email: " + updateCandidateModel.getEmail());
        holder.id.setText("ID: " + updateCandidateModel.getId());
        holder.role.setText("Role: " + updateCandidateModel.getRole());

        String image = null;
        image = updateCandidateModel.getImage();
        Picasso.get().load(image).into(holder.imageView);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imageView.getContext()).
            }
        });


    }

    @Override
    public int getItemCount() {
        return UpdateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView id, name, email, role;
        View v;
        Button delete, edit;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_update);
            name = itemView.findViewById(R.id.t1_update);
            email = itemView.findViewById(R.id.t2_update);
            id = itemView.findViewById(R.id.t3_update);
            role = itemView.findViewById(R.id.t4_update);

            delete = itemView.findViewById(R.id.btnDelete);
            edit = itemView.findViewById(R.id.btnEdit);
        }
    }



}
