package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PasswordLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);
        getSupportActionBar().hide();
    }
}