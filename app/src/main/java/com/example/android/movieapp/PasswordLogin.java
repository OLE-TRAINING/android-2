package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PasswordLogin extends AppCompatActivity {

    Sign_in_error viewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

         viewPassword = (Sign_in_error) findViewById(R.id.viewPasswordLogin);

    }


    public void buttonPasswordLogin(View view) {

        viewPassword.setVisible(view);
    }
}
