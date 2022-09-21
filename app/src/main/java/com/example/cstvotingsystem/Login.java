package com.example.cstvotingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private static final Pattern EMAIL_ADDRESS = Pattern.compile("^[0-9]+\\.cst@rub\\.edu\\.bt",Pattern.CASE_INSENSITIVE);
    EditText email,password;
    private CheckBox showpassword;
    Button loginBtn;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseUser firebaseUser;
    TextView registerLink,forgotPass;
    ProgressBar progressBar;
    private static final String TAG = "Login";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        showpassword =findViewById(R.id.showpassword);

        email = findViewById(R.id.loginID);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.gotoLogin);
        forgotPass = findViewById(R.id.forgotPassword);
        registerLink = findViewById(R.id.register_text);
        progressBar = findViewById(R.id.progressbar);

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Registration.class));
                }
            });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start alert dialog
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Receive Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset is Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error! Reset Link Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();
            }
        });

        fAuth = FirebaseAuth.getInstance();

        // login User
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = email.getText().toString();
                String textPass = password.getText().toString();

                if (TextUtils.isEmpty(textEmail)) {
                    email.setError("Email is required");
                    email.requestFocus();
                }
                else if (!EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    // Toast.makeText(Registration.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    email.setError("Please enter a valid email address");

                    email.requestFocus();
                }else if (TextUtils.isEmpty(textPass)) {
                    password.setError("Password is Required.");
                    password.requestFocus();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    LoginUser(textEmail, textPass);
                }
            }
        });
       /* loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail()) {
                    return;
                }
                checkField(email);
                checkField(password);
                Log.d("TAG", "onClick: " + email.getText().toString());

              //  pd.setTitle("Logging in...");
              //  pd.show();

                if(valid) {
                    fAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (fAuth.getCurrentUser().isEmailVerified()) {
                             //   pd.dismiss();
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                               // checkUserAccessLevel(authResult.getUser().getUid());
                            }
                            else {
                            //    pd.dismiss();
                                Toast.makeText(Login.this, "Please Verify Your Email To Login.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                         //   pd.dismiss();
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });*/
    }

    private void LoginUser(String textEmail, String textPass) {
        fAuth.signInWithEmailAndPassword(textEmail, textPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                }
                else{
                        try {
                            throw task.getException();


                  //  Toast.makeText(Login.this, "Something is wrong", Toast.LENGTH_SHORT).show();

                        } catch (FirebaseAuthInvalidUserException e){
                            email.setError("User does not exits or is no longer valid register again");
                            email.requestFocus();
                        } catch (FirebaseAuthInvalidCredentialsException e){
                            email.setError("Invalid Credintial. Kindly, Check and re-enter");
                            email.requestFocus();
                        } catch (Exception e){
                            Log.e(TAG, e.getMessage());
                        }
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }



  /*  private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } /*else if (!EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        }
        else {
            email.setError(null);
            return true;
        }
    }
    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }*/



}