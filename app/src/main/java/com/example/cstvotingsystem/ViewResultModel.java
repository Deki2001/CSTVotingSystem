package com.example.cstvotingsystem;

public class ViewResultModel {
    String Id;
    String Email;
    String Name;
    String Role;
    String Image;
    int Vote;


    public ViewResultModel(String id, String email, String name, String role, String image, int vote) {
        Id = id;
        Email = email;
        Name = name;
        Role = role;
        Image = image;
        Vote = vote;
    }
    public ViewResultModel(){

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
    public int getVote() {
        return Vote;
    }

    public void setVote(int vote) {
        Vote = vote;

    }

}
