package com.example.cstvotingsystem;

public class ReadWriteUserDetails {
    public String userid, username, gender, useremail, userpassword,isVote;

    public ReadWriteUserDetails (String user_id, String user_name, String text_gender, String user_email, String user_password, String isVote){
        this.userid = user_id;
        this.username = user_name;
        this.gender = text_gender;
        this.useremail = user_email;
        this.userpassword = user_password;
        this.isVote = isVote;
    }
}
