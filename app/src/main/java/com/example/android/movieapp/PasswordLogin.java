package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordLogin extends AppCompatActivity {

    SignInError viewPassword;
    EditText editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

        viewPassword = (SignInError) findViewById(R.id.view_password_login);

        editPassword = (EditText) findViewById(R.id.password_edit_password);

    }

    public void buttonPasswordLogin(View view) {

        String password = editPassword.getText().toString();

        viewPassword.setVisible(view);

    }
}
