package com.example.android.movieapp.loginScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.User;
import com.example.android.movieapp.viewModelLogin.LoginViewModel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginActivity extends AppCompatActivity {

    private EditText emailItem;
    private String email;
    Intent passwordLoginIntent;
    Intent newAccountIntent;
    private Intent tokenIntent;

    private AlertDialog alerta;
    private android.support.v7.app.AlertDialog.Builder builder;

    private NewAccountError emailView;
    private LoginViewModel loginViewModel;
    private Loading loadingView;

    public final static String EMAIL_MESSAGE =
            "com.example.mysampleapp.MESSAGE";

    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        emailView = (NewAccountError) findViewById(R.id.edit_email_login);
        loadingView = (Loading) findViewById(R.id.loadingLogin);

        emailView.setValue(getString(R.string.invalid_email), getString(R.string.email_hint_login));

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        // loginViewModel.getIsloadingObservable().observe(observadorDoLoading);
        //
        builder = new AlertDialog.Builder(this);
    }

    Observer<Boolean> observerValidEmail = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            emailView.setVisibleError(getView(), aBoolean);
        }
    };

    Observer<Boolean> observerLoading = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            loadingView.setLoadingView(getView(), aBoolean);
        }
    };

    Observer<String> observerStatus = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String status) {
            builder.setTitle("Erro");
            builder.setMessage(status);
            alerta = builder.create();
            alerta.show();
        }
    };


    Observer<User> observerEmail = new Observer<User>() {
        @Override
        public void onChanged(@Nullable User usuario) {


            if (new String(usuario.getRegistrationStatus()).equals("REGISTERED")) {
                passwordLoginIntent.putExtra(EMAIL_MESSAGE, usuario);
                startActivity(passwordLoginIntent);

            } else if (new String(usuario.getRegistrationStatus()).equals("PENDING")) {
                tokenIntent.putExtra(EMAIL_MESSAGE, usuario);
                startActivity(tokenIntent);
            } else if (new String(usuario.getRegistrationStatus()).equals("INEXISTENT")) {
                newAccountIntent.putExtra(EMAIL_MESSAGE, usuario);
                startActivity(newAccountIntent);
            }

        }
    };


    public View getView() {
        return this.view;
    }

    public void setView(View v) {
        this.view = v;
    }


    public void validarEmail(View view) throws InterruptedException, ExecutionException, TimeoutException {

        email = emailView.getEditText();

        this.setView(view);
        this.loginViewModel.init(email);

        loginViewModel.getEmailValid().observe(this, observerValidEmail);
        loginViewModel.getNextScreen().observe(this, observerEmail);
        loginViewModel.getLoading().observe(this, observerLoading);
        loginViewModel.getStatus().observe(this, observerStatus);

        passwordLoginIntent = new Intent(this, PasswordLoginActivity.class);
        newAccountIntent = new Intent(this, NewAccountActivity.class);
        tokenIntent = new Intent(this, SignUpContinueActivity.class);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginViewModel.getNextScreen().removeObserver(observerEmail);
    }


}
