package com.example.cstvotingsystem;

import com.google.firebase.firestore.DocumentReference;

public class CandidateModel {



    String Id;
    String Email;
    String Name;
    String Role;
    String Image;
    String VName, VImage, VId;


    public CandidateModel(String id, String email, String name, String role, String image) {
        Id = id;
        Email = email;
        Name = name;
        Role = role;
        Image = image;
    }
    public CandidateModel(){

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}