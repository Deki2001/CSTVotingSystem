package com.example.cstvotingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CandidateRegisterAdapter extends RecyclerView.Adapter<CandidateRegisterAdapter.ViewHolder> {

    Context context;
    List<CandidateModel> candidateModelList;

    public CandidateRegisterAdapter(Context context, List<CandidateModel> candidateModelList) {
        this.context = context;
        this.candidateModelList = candidateModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_candidate_list,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CandidateModel candidateModel = candidateModelList.get(position);
        holder.name.setText("Name: " + candidateModel.getName());
        holder.email.setText("Email: " + candidateModel.getEmail());
        holder.id.setText("ID: " + candidateModel.getId());
        holder.role.setText("Role: " + candidateModel.getRole());

        String image = null;
        image = candidateModel.getImage();
        Picasso.get().load(image).into(holder.imageView);

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Intent intent = new Intent(,CandidateBigViewActivity.class);
//                intent.putExtra("singleImage", candidateModel.getImage());
//                intent.putExtra("Name", candidateModel.getName());
//                intent.putExtra("ID", candidateModel.getId());
//                intent.putExtra("Email", candidateModel.getEmail());
//                intent.putExtra("Role", candidateModel.getRole());

//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);



            }
        });

        //Big picture



    }

    @Override
    public int getItemCount() {

        return candidateModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView id, name, email, role;
        View v;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_recycler_id);
            name = itemView.findViewById(R.id.t1);
            email = itemView.findViewById(R.id.t2);
            id = itemView.findViewById(R.id.t3);
            role = itemView.findViewById(R.id.t4);

            //For big candidate view

            v= itemView;


        }
    }
}