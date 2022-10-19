package com.example.cstvotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity { //sgg

    TextView loginLink;
    private static final Pattern EMAIL_ADDRESS = Pattern.compile
            ("^[0-9]+\\.cst@rub\\.edu\\.bt",Pattern.CASE_INSENSITIVE);
    EditText userid, userName, userEmail, userPassword, confirmPassword;
    Button userregister_Btn;
    RadioGroup genderbtn;
    RadioButton gender;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private static final  String TAG = "Registration";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginLink = findViewById(R.id.login_text);
        userid = findViewById(R.id.uid);
        userName = findViewById(R.id.user_fullname);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        confirmPassword = findViewById(R.id.confirm_password);
        userregister_Btn = findViewById(R.id.userregister_btn);
        genderbtn = findViewById(R.id.genderGroup);
        progressBar = findViewById(R.id.progressbar);


        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        userregister_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //obtain the enter data
                String user_id = userid.getText().toString();
                String user_email = userEmail.getText().toString();
                String user_password = userPassword.getText().toString();
                String confirm_password = confirmPassword.getText().toString();
                String user_Name = userName.getText().toString();

                String isVote = "False";
//                String isvt= isVote.toString();




                int genderselected = genderbtn.getCheckedRadioButtonId();
                gender = findViewById(genderselected);
                String  textGender = gender.getText().toString();





                if (TextUtils.isEmpty(user_id)) {
                //    Toast.makeText(Registration.this, "Please eneter your Student ID", Toast.LENGTH_SHORT).show();
                    userid.setError("User ID is Required.");
                    userid.requestFocus();
                }else if(user_id.length() < 8) {
                    userid.setError("UID Must be more than 7 Characters");
                    userid.requestFocus();
                }

                else if (TextUtils.isEmpty(user_Name)) {
                   // Toast.makeText(Registration.this, "Please eneter your Full Name", Toast.LENGTH_SHORT).show();
                    userName.setError("Full Name is Required.");
                    userName.requestFocus();
                }

                else if (TextUtils.isEmpty(user_email)) {
                    userEmail.setError("Email is Required.");
                    userEmail.requestFocus();
                }else if (!EMAIL_ADDRESS.matcher(user_email).matches()) {
                   // Toast.makeText(Registration.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    userEmail.setError("Please enter a valid email address");

                    userEmail.requestFocus();
                }
                else if (genderbtn.getCheckedRadioButtonId() ==-1){

                    Toast.makeText(Registration.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    genderbtn.requestFocus();

                }

                else if (TextUtils.isEmpty(user_password)) {
                    userPassword.setError("Password is Required.");
                    userPassword.requestFocus();
                }

                else if (user_password.length() < 6) {
                    userPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

               else if (TextUtils.isEmpty(confirm_password)) {
                    confirmPassword.setError("Confirm Password is Required.");
                    return;
                }

                else if (!user_password.equals(confirm_password)) {
                    confirmPassword.setError("Password Do not match.");
                }
                else {


                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(user_id, user_Name,textGender,user_email,user_password,isVote);  // user
                }


            }
        });

    }

    private void registerUser(String user_id, String user_name,String textGender, String user_email, String user_password, String isvote) {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //Create User Profile
        fAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //     Toast.makeText(Registration.this, "User Registration successful", Toast.LENGTH_SHORT).show();
                    FirebaseUser fuser = fAuth.getCurrentUser();

                    //Enter User data into the real time database
                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(user_id, user_name, textGender, user_email, user_password, isvote);

                    //Extracting User reference from Database for "Registered User"
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered User");

                    referenceProfile.child(fuser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isComplete()){
                                //send varification email
                                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Registration.this, "User Registered. Verification Email Has been Sent." +
                                                "\nVerify Your Email To Login.", Toast.LENGTH_LONG).show();
                                        userid.setText("");
                                        userName.setText("");
                                        userEmail.setText("");
                                        userPassword.setText("");
                                        confirmPassword.setText("");
                                        genderbtn.clearCheck();
                                        progressBar.setVisibility(View.GONE);
                                       /*Intent intent = new Intent(Registration.this, UserPage.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity( intent);
                                        finish();*/

                                        DocumentReference documentReference = fStore.collection("Users").document(fuser.getUid());
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("UserID", user_id);
                                        user.put("FullName", user_name);
                                        user.put("UserEmail", user_email);
                                        user.put("Gender", textGender);
                                        //specify if the user is Admin
                                        user.put("isUser","0");
                                        user.put("isVote", "false");
                                       // user.put("isStart", "false");







                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Log.d("TAG", "onSuccess: user Profile is created");

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("TAG", "onFailure: " + e.toString());
                                            }
                                        });


                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("TAG", "onFailure: Email not sent " + e.getMessage());
                                    }
                                });


                            }
                            else{
                                Toast.makeText(Registration.this, "User Registration Failed. Please Try Again", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }

                        }
                    });

                }else {
                    try {
                        throw task.getException();
                    }  catch (FirebaseAuthUserCollisionException e){
                        userEmail.setError("User is already register with this email ");
                        userEmail.requestFocus();
                        progressBar.setVisibility(View.GONE);

                    } catch (Exception e){
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(Registration.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }


                }

            }
        });
    }



}
