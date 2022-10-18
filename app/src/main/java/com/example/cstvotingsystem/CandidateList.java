package com.example.cstvotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

public class CandidateList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);

        PhotoView photoView = findViewById(R.id.image_recycler_id);
        photoView.setImageResource(R.drawable.profile_pic);
    }
}