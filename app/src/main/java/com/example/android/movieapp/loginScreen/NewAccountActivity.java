package com.example.android.movieapp.loginScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.connect.User;
import com.example.android.movieapp.viewModelLogin.NewAccountViewModel;

public class NewAccountActivity extends AppCompatActivity {

    private NewAccountError nameView;
    NewAccountError userView;
    NewAccountError passwordView;
    TextView emailView;
    private View view;
    private String name;
    private String password;
    private String user;
    private User userObj;
    private String email;
    private Intent tokenIntent;
    private Loading loadingView;
    private AlertDialog alerta;
    AlertDialog.Builder builder;

    public final static String EMAIL_MESSAGE =
            "com.example.mysampleapp.MESSAGE";

    private NewAccountViewModel newAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_account);
        nameView = (NewAccountError) findViewById(R.id.name_new_account);
        nameView.setValue(getString(R.string.invalid_name), getString(R.string.complete_name));
        nameView.setLength(50);
        userView = (NewAccountError) findViewById(R.id.user_new_account);
        userView.setValue(getString(R.string.username_required), getString(R.string.name_user));
        userView.setLength(15);
        passwordView = (NewAccountError) findViewById(R.id.password_new_account);
        passwordView.setValue(getString(R.string.invalid_password), getString(R.string.password_hint));
        passwordView.setPasswordType();
        passwordView.setLength(10);
        loadingView = (Loading) findViewById(R.id.loading_new_account);

        emailView = (TextView) findViewById(R.id.email_new_account);

        Intent intent = getIntent();
        email = intent.getStringExtra(LoginActivity.EMAIL_MESSAGE);
        emailView.setText(email);

        newAccountViewModel = ViewModelProviders.of(this).get(NewAccountViewModel.class);

        builder = new AlertDialog.Builder(this);

    }

    Observer<Boolean> observerValidName = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            nameView.setVisibleError(getView(), aBoolean);
        }
    };

    Observer<Boolean> observerValidUserName = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            userView.setVisibleError(getView(), aBoolean);
        }
    };

    Observer<Boolean> observerValidPassword = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            passwordView.setVisibleError(getView(), aBoolean);
        }
    };

    Observer<String> observerStatus = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String status) {
            if (new String(status).equals("Ok")) {
                tokenIntent.putExtra(EMAIL_MESSAGE, email);
                startActivity(tokenIntent);
            } else {
                builder.setTitle("Erro");

                builder.setMessage(status);
                alerta = builder.create();
                alerta.show();
                loadingView.setLoadingView(getView(), false);
            }
        }
    };

    Observer<Boolean> observerLoading = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            loadingView.setLoadingView(getView(), aBoolean);
        }
    };


    public void buttonNewAccount(View view) {

        name = nameView.getEditText();
        user = userView.getEditText();
        password = passwordView.getEditText();
        userObj = new User();
        userObj.setEmail(email);
        userObj.setCompleteName(name);
        userObj.setUsername(user);
        userObj.setPassword(password);
        this.setView(view);
        System.out.println("NEWACCOUNT");
        newAccountViewModel.init(userObj);
        newAccountViewModel.getNameValidation().observe(this, observerValidName);
        newAccountViewModel.getUserNameValidation().observe(this, observerValidUserName);
        newAccountViewModel.getPasswordValidation().observe(this, observerValidPassword);
        newAccountViewModel.getStatus().observe(this, observerStatus);
        newAccountViewModel.getLoading().observe(this, observerLoading);

        tokenIntent = new Intent(this, TokenValidationActivity.class);
    }

    public View getView() {
        return this.view;
    }

    public void setView(View v) {
        this.view = v;
    }

    public void back_activity(View view) {
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadingView.setLoadingView(getView(), false);
    }
}
