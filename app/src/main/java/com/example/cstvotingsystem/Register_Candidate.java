package com.example.cstvotingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register_Candidate extends AppCompatActivity {


    private EditText Mid, Mname, Memail, Mrole;
    private Button viewList, register;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_candidate);
        viewList = findViewById(R.id.show_list);

        Mid = findViewById(R.id.user_id);
        Mname = findViewById(R.id.user_fullname);
        Memail = findViewById(R.id.user_email);
        Mrole = findViewById(R.id.candidate_role);
        register = findViewById(R.id.register_btn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = Mid.getText().toString();
                String name = Mname.getText().toString();
                String email = Memail.getText().toString();
                String role = Mrole.getText().toString();

                HashMap<String, String> userMap = new HashMap<>();

                userMap.put("name", name);
                userMap.put("id", id);
                userMap.put("email",email);
                userMap.put("Role",role);

                root.setValue(userMap);
            }
        });
    }

    public void viewList(View view) {
        startActivity(new Intent(getApplicationContext(), ViewCandidates.class));

    }
}