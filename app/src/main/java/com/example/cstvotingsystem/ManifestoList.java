package com.example.cstvotingsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;

public class ManifestoList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);

        PhotoView photoView = findViewById(R.id.image_recycler_id);
        photoView.setImageResource(R.drawable.profile_pic);
    }
}