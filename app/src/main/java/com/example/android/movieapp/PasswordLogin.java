package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PasswordLogin extends AppCompatActivity {

    SignInError viewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

         viewPassword = (SignInError) findViewById(R.id.view_password_login);

    }


    public void buttonPasswordLogin(View view) {

        viewPassword.setVisible(view);
    }
}
