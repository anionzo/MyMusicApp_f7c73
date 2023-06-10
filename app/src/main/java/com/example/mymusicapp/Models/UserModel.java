package com.example.mymusicapp.Models;

public class UserModel {
    private String userName;
    private String email;
    private String pass;

    public UserModel(String userName, String email, String pass) {
        this.userName = userName;
        this.email = email;
        this.pass = pass;
    }

    public UserModel(UserModel userModel) {
        this.userName = userModel.userName;
        this.email = userModel.email;
        this.pass = userModel.pass;

    }

    public UserModel() {

    }
    public interface OnUserModelCompleteListener {
        void onComplete(UserModel userModel);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
