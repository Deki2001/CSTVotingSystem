package com.example.cstvotingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.util.regex.Pattern;

public class Register_Candidate extends AppCompatActivity {
    // register candidates
    private static final Pattern EMAIL_ADDRESS = Pattern.compile
            ("^[0-9]+\\.cst@rub\\.edu\\.bt",Pattern.CASE_INSENSITIVE);
    EditText Mid, Mname, Memail, Mrole;
    Button viewList, register;
    ImageButton imageButton;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("Students");
    FirebaseStorage mStroage = FirebaseStorage.getInstance();
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
                String img = imageButton.toString();

                String Cid = Mid.getText().toString();
                String Cname = Mname.getText().toString();
                String Cemail = Memail.getText().toString();
                String Crole = Mrole.getText().toString();
                if (TextUtils.isEmpty(img)) {
                    //    Toast.makeText(Registration.this, "Please eneter your Student ID", Toast.LENGTH_SHORT).show();
                    Mid.setError("User Image is required.");
                }
                if (TextUtils.isEmpty(id)) {
                    //    Toast.makeText(Registration.this, "Please eneter  Student ID", Toast.LENGTH_SHORT).show();
                    Mid.setError("User ID is Required.");
                }else if(id.length() < 8) {
                    Mid.setError("UID Must be more than 7 Characters");
                    Mid.requestFocus();
                }if (TextUtils.isEmpty(name)) {
                    //    Toast.makeText(Registration.this, "Please eneter Nmae", Toast.LENGTH_SHORT).show();
                    Mid.setError("User ID is Required.");
                }
                else if (TextUtils.isEmpty(role)) {
                    Mrole.setError("Email is Required.");
                    Mrole.requestFocus();
                }

                else if (TextUtils.isEmpty(email)) {
                    Memail.setError("Email is Required.");
                    Memail.requestFocus();
                }else if (!EMAIL_ADDRESS.matcher(email).matches()) {
                    // Toast.makeText(Registration.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    Memail.setError("Please enter a valid email address");

                    Memail.requestFocus();
                }else {
                    RegisterCandidate(id,name,email,role);
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

    private void RegisterCandidate(String id, String name, String email, String role) {


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

                            Mid.setText("");
                            Mname.setText("");
                            Memail.setText("");
                            Mrole.setText("");
                            imageButton.setEnabled(id.isEmpty()||name.isEmpty()||email.isEmpty()||role.isEmpty());
                            progressDialog.dismiss();

                                 /*   if (TextUtils.isEmpty(id)) {
                                        //    Toast.makeText(Registration.this, "Please eneter your Student ID", Toast.LENGTH_SHORT).show();
                                        Mid.setError("User ID is Required.");
                                    }else if(id.length() < 8) {
                                        Mid.setError("UID Must be more than 7 Characters");
                                        Mid.requestFocus();
                                    }else if (TextUtils.isEmpty(role)) {
                                        Mrole.setError("Email is Required.");
                                        Mrole.requestFocus();
                                    }

                                    else if (TextUtils.isEmpty(email)) {
                                        Memail.setError("Email is Required.");
                                        Memail.requestFocus();
                                    }else if (!EMAIL_ADDRESS.matcher(email).matches()) {
                                        // Toast.makeText(Registration.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                                        Memail.setError("Please enter a valid email address");

                                        Memail.requestFocus();
                                    }*/
                            Toast.makeText(Register_Candidate.this, "Successful", Toast.LENGTH_SHORT).show();

                            //Intent intent = new Intent(Register_Candidate.this,ViewCandidates.class);
                            // startActivity(intent);



                        }
                    });

                }





            });
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){

            image = data.getData();
            imageButton.setImageURI(image);
        }


    }



//    public void viewList(View view) {
//        startActivity(new Intent(getApplicationContext(), ViewCandidates.class));
//
//    }
}