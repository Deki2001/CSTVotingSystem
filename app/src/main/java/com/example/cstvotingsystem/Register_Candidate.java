package com.example.cstvotingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Register_Candidate extends AppCompatActivity {


    private EditText Mid, Mname, Memail, Mrole;
    private Button viewList, register;
    private ImageButton imageButton;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Students");
    private FirebaseStorage mStroage = FirebaseStorage.getInstance();
    private static final int Gallery_Code = 1;
    Uri image = null;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_candidate);
        viewList = findViewById(R.id.show_list);

        imageButton = findViewById(R.id.imageButton);
        Mid = findViewById(R.id.user_id);
        Mname = findViewById(R.id.user_fullname);
        Memail = findViewById(R.id.user_email);
        Mrole = findViewById(R.id.candidate_role);
        register = findViewById(R.id.register_btn);
        progressDialog = new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = Mid.getText().toString().trim();
                String name = Mname.getText().toString().trim();
                String email = Memail.getText().toString().trim();
                String role = Mrole.getText().toString().trim();

                if(!(id.isEmpty() && name.isEmpty() && email.isEmpty() && role.isEmpty() && image != null)){


                    progressDialog.setTitle("uploading...");
                    progressDialog.show();

                    StorageReference filepath = mStroage.getReference().child("imagePost").child(image.getLastPathSegment());
                    filepath.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                         Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                             @Override
                             public void onComplete(@NonNull Task<Uri> task) {

                                 String t = task.getResult().toString();

                                 DatabaseReference newPost = root.push();

                                 newPost.child("Id").setValue(id);
                                 newPost.child("Name").setValue(name);
                                 newPost.child("Email").setValue(email);
                                 newPost.child("Role").setValue(role);
                                 newPost.child("Image").setValue(task.getResult().toString());
                                 progressDialog.dismiss();

                             }
                         });

                        }
                    });
                }
//                HashMap<String, String> userMap = new HashMap<>();
//
//                userMap.put("name", name);
//                userMap.put("id", id);
//                userMap.put("email",email);
//                userMap.put("Role",role);
//
//                root.setValue(userMap);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){

            image = data.getData();
            imageButton.setImageURI(image);
        }


    }



    public void viewList(View view) {
        startActivity(new Intent(getApplicationContext(), ViewCandidates.class));

    }
}