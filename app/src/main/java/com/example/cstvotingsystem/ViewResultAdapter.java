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

public class ViewResultAdapter extends RecyclerView.Adapter<ViewResultAdapter.ViewHolder>{

        Context context;
        List<ViewResultModel> ResultModelList;

        public ViewResultAdapter(Context context, List<ViewResultModel> candidateModelList) {
            this.context = context;
            this.ResultModelList = candidateModelList;
        }

        @NonNull
        @Override
        public ViewResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list,parent,false);

            return new ViewHolder(v);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewResultModel ResultModel = ResultModelList.get(position);
        holder.name.setText("Name: " + ResultModel.getName());
       // holder.email.setText("Email: " + ResultModel.getEmail());
        holder.id.setText("ID: " + ResultModel.getId());
        holder.role.setText("Role: " +ResultModel.getRole());
        holder.vote.setText("Votes: " + ResultModel.getVote());



        String image = null;
        image = ResultModel.getImage();
        Picasso.get().load(image).into(holder.imageView);

    }



        @Override
        public int getItemCount() {

            return ResultModelList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView id, name, email, role, vote;
            View v;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.image_result);
                name = itemView.findViewById(R.id.t1_result);
             //   email = itemView.findViewById(R.id.t2_result);
                id = itemView.findViewById(R.id.t3_result);
                role = itemView.findViewById(R.id.t4_result);
                vote = itemView.findViewById(R.id.t5_result);


                //For big candidate view

                v= itemView;


            }
        }
}
