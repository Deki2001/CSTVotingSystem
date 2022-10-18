package com.example.cstvotingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Register_Candidate extends AppCompatActivity{
    private  static final String TAG = "Register_Candidate";
    // register candidates
    EditText Mid, Mname, Memail;
    CandidateModel member;
    Button viewList, register;

   // String item;
    private Spinner spinner;
    ImageButton imageButton;
    FirebaseDatabase db = FirebaseDatabase.getInstance();//database
    DatabaseReference root = db.getReference().child("Students");//reference

    SpinnerDataSave spinnerDataSave;
    int maxid = 0;
    FirebaseStorage mStroage = FirebaseStorage.getInstance();
    private static final int Gallery_Code = 1;
    Uri image = null;

    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_candidate);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewList = findViewById(R.id.show_list);
        imageButton = findViewById(R.id.imageButton);
        Mid = findViewById(R.id.user_id);
        Mname = findViewById(R.id.user_fullname);
        Memail = findViewById(R.id.user_email);
        spinner = findViewById(R.id.spinner1);

        member = new CandidateModel();

     //   Mrole = findViewById(R.id.candidate_role);

        register = findViewById(R.id.register_btn);
        spinnerDataSave = new SpinnerDataSave();

        progressDialog = new ProgressDialog(this);

         spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dropdown,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UpdateCandidateView.class));

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_Code);
            }
        });



        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    maxid = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = Mid.getText().toString().trim();
                String name = Mname.getText().toString().trim();
                String email = Memail.getText().toString().trim();
                // String role = item.trim();
                String role = spinner.getSelectedItem().toString();


                Toast.makeText(Register_Candidate.this, "Value stored successfully", Toast.LENGTH_SHORT).show();

                root.child(String.valueOf(maxid+1)).setValue(spinnerDataSave);

                if (!(id.isEmpty() && name .isEmpty() && email.isEmpty() && image != null)) {


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
                                    String vid;

                                    DatabaseReference newPost = root.child(id);
                                    newPost.child("Id").setValue(id);
                                    newPost.child("Name").setValue(name);
                                    newPost.child("Email").setValue(email);
                                    newPost.child("Role").setValue(role);
                                     newPost.child("Vote").setValue(0);
                                    newPost.child("Image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(Register_Candidate.this, ViewCandidates.class);
                                    startActivity(intent);

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
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }
        if (item.getItemId() == R.id.user_profile) {
            startActivity(new Intent(getApplicationContext(), UserProfile.class));
        }
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(getApplicationContext(), AboutPage.class));
        }
        if (item.getItemId() == R.id.home){
            startActivity(new Intent(getApplicationContext(), UserPage.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Code && resultCode == RESULT_OK) {

            image = data.getData();
            imageButton.setImageURI(image);
        }


    }

   /* @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
   // item = spinner.getSelectedItem().toString();


    }*/

   /* @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}



