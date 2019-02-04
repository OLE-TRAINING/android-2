package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private EditText emailItem;
    private String email;
    private NewAccountError emailView;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        emailView = (NewAccountError) findViewById(R.id.edit_email_login);
        emailView.setValue(getString(R.string.invalid_email),getString(R.string.email_hint));


    }

    public static boolean validarEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void validarEmail(View view) {

        email=emailView.getEditText();

        if(validarEmail(email)){

        }
        else{

            emailView.setValue(getString(R.string.invalid_email),getString(R.string.example_email));

            emailView.setVisibleError(view);

            emailView.setText();

        }
    }
}
