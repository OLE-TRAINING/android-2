package com.example.android.movieapp.connect;

import java.io.Serializable;

public class User implements Serializable {

    private String email;
    private String completeName;
    private String username;
    private String registrationStatus;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord;
    }
}
