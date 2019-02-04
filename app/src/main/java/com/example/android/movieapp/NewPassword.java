package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewPassword extends AppCompatActivity {

    NewAccountError tokenView;
    NewAccountError newPasswordView;

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        tokenView = (NewAccountError) findViewById(R.id.token_new_password);
        tokenView.setValue(getString(R.string.invalid_token),getString(R.string.validation_token));

        newPasswordView = (NewAccountError) findViewById(R.id.new_password_edit_new_passaword);
        newPasswordView.setValue(getString(R.string.invalid_password),getString(R.string.new_password_hint));


    }



    public static boolean passwordValidation(String name){
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }


    public void launchNewPassworod(View view) {

        int tokenSize = tokenView.getLength();
        String password=newPasswordView.getEditText();
        if(tokenSize!=6){
            tokenView.setVisibleError(view);
        }

        if(passwordValidation(password)&&newPasswordView.getLength()>=6){

        }

        else{
            newPasswordView.setVisibleError(view);
        }
    }
}
