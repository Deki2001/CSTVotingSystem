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

public class ManifestoAdapter extends RecyclerView.Adapter<ManifestoAdapter.ViewHolder> {

    Context context;
    List<ManifestoModel> manifestoModelList;

    public ManifestoAdapter(Context context, List<ManifestoModel> manifestoModelList) {
        this.context = context;
        this.manifestoModelList = manifestoModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_manifesto_list,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ManifestoModel manifestoModel = manifestoModelList.get(position);
        holder.FName.setText(manifestoModel.getName());

        String imageUrl = null;
        imageUrl=manifestoModel.getImage();
        Picasso.get().load(imageUrl).into(holder.maniImage);

    }

    @Override
    public int getItemCount() {
        return manifestoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView maniImage;
        TextView FName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            maniImage=itemView.findViewById(R.id.manifesto_recycler_id);
            FName=itemView.findViewById(R.id.namemani);
        }
    }
}
