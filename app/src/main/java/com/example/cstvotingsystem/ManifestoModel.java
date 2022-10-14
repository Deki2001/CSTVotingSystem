package com.example.cstvotingsystem;

public class ManifestoModel {
    String Name;
    String Image;

    public ManifestoModel(String name, String image) {
        Name = name;
        Image = image;
    }

    public ManifestoModel() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}