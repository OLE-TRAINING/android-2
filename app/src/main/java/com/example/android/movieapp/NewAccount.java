package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewAccount extends AppCompatActivity {

   private NewAccountError nameView;
    NewAccountError userView;
    NewAccountError passwordView;
    private String name;
    private String password;
    private String user;
    private static final String NAME_PATTERN = "^[a-zA-Z]+$";
    private static final String USER_PATTERN = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";
    private static final Pattern pattern = Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern pattern2 = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern pattern3 = Pattern.compile(USER_PATTERN, Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_account);
        nameView = (NewAccountError) findViewById(R.id.name_new_account);
        nameView.setValue(getString(R.string.invalid_name),getString(R.string.complete_name));
        nameView.setLength(50);
        userView = (NewAccountError) findViewById(R.id.user_new_account);
        userView.setValue(getString(R.string.username_required),getString(R.string.name_user));
        userView.setLength(15);
        passwordView = (NewAccountError) findViewById(R.id.password_new_account);
        passwordView.setValue(getString(R.string.invalid_password),getString(R.string.password_hint));
        passwordView.setPasswordType();
        passwordView.setLength(10);

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_D:

                return true;
            case KeyEvent.KEYCODE_F:

                return true;
            case KeyEvent.KEYCODE_J:

                return true;
            case KeyEvent.KEYCODE_K:

                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    public static boolean nameValidation(String name){
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean userValidation(String user){
        Matcher matcher = pattern3.matcher(user);
        return matcher.matches();
    }

    public static boolean passwordValidation(String name){
        Matcher matcher = pattern2.matcher(name);
        return matcher.matches();
    }


    public void buttonNewAccount(View view) {

        name=nameView.getEditText();
        user = userView.getEditText();
        password=passwordView.getEditText();
        if(nameValidation(name)) {

        }

        else {
            nameView.setVisibleError(view);
        }

        if(passwordValidation(password)&&passwordView.getLength()>=6){

        }

        else {
            passwordView.setVisibleError(view);
        }

        if(userValidation(user)){

        }

        else {
            userView.setVisibleError(view);
        }
    }
}
