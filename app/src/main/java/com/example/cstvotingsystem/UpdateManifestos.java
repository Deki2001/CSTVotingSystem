package com.example.cstvotingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateManifestos extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageButton maniButton;
    EditText name;
    Button add;

    private static final int Gallery_Code = 1;
    Uri imageUrl = null;

ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_manifestos);

        maniButton = findViewById(R.id.manifestButton);
        name = findViewById(R.id.fname);
        add = findViewById(R.id.Add);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Manifestos");
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        maniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageUrl=data.getData();
            maniButton.setImageURI(imageUrl);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = name.getText().toString().trim();


                if(!(fname.isEmpty() && imageUrl !=null))
                {

                    progressDialog.setTitle("Adding...");
                    progressDialog.show();
                    StorageReference filepath=mStorage.getReference().child("manifestoPost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> dounloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();

                                    DatabaseReference newPost=mRef.push();

                                    newPost.child("Name").setValue(fname);

                                    newPost.child("Image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();
                                    Toast.makeText(UpdateManifestos.this, "Manifesto Added", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        });
    }


    public void viewManifesto(View view) {
        startActivity(new Intent(getApplicationContext(), ManifestoRecycleview.class));

    }
}