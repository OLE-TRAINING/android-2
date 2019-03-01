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
import com.example.android.movieapp.connect.NewPasswordObj;
import com.example.android.movieapp.viewModel.NewPasswordViewModel;

public class NewPasswordActivity extends AppCompatActivity {

    public final static String EMAIL_MESSAGE =
            "com.example.mysampleapp.MESSAGE";

    NewAccountError tokenView;
    private TextView emailView;
    private View view;
    private Loading loadingView;
    NewAccountError newPasswordView;
    NewAccountError confirmPasswordView;
    private String email;
    private AlertDialog alerta;
    AlertDialog.Builder builder;
    private NewPasswordObj newPasswordObj;
    private NewPasswordViewModel newPasswordViewModel;

    private Intent passwordLoginIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);


        loadingView = (Loading) findViewById(R.id.loading_new_password);
        tokenView = (NewAccountError) findViewById(R.id.token_new_password);
        tokenView.setValue(getString(R.string.invalid_token),getString(R.string.validation_token));
        tokenView.setLength(6);

        newPasswordView = (NewAccountError) findViewById(R.id.new_password_edit_new_passaword);
        newPasswordView.setValue(getString(R.string.invalid_password),getString(R.string.new_password_hint));
        newPasswordView.setPasswordType();

        confirmPasswordView = (NewAccountError) findViewById(R.id.confirm_new_password);
        confirmPasswordView.setValue(getString(R.string.invalid_password),getString(R.string.confirm_new_password));
        confirmPasswordView.setPasswordType();

        newPasswordViewModel = ViewModelProviders.of(this).get(NewPasswordViewModel.class);

        Intent intent = getIntent();

        email = intent.getStringExtra(PasswordLoginActivity.NEW_PASSWORD_MESSAGE);

        emailView = (TextView) findViewById(R.id.email_new_password);
        emailView.setText(email);

        builder = new AlertDialog.Builder(this);


    }

    Observer<Boolean> observerInvalidToken = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean bool) {
            tokenView.setVisibleError(getView(),bool);
        }
    };

    Observer<String> observerState = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String status) {
            if((status).equals("Ok")){
                passwordLoginIntent.putExtra(EMAIL_MESSAGE, email);
                startActivity(passwordLoginIntent);
            }

            else if ((status).equals("Token de validação incorreto")){

                tokenView.setValue(getString(R.string.incorrect_token),getString(R.string.validation_token));
                tokenView.setVisibleError(getView(),false);
            }
            else {
                builder.setTitle("Erro");
                //define a mensagem
                builder.setMessage(status);

                alerta = builder.create();
                alerta.show();
            }
        }


    };

    Observer<String> observerInvalidPassword = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String error) {

            if((error).equals("InvalidPassword")) {
                newPasswordView.setVisibleError(getView(), false);
                confirmPasswordView.setVisibleError(getView(),false);
            }

            else if ((error).equals("DifferentPassword")){

                confirmPasswordView.setValue(getString(R.string.password_confirm_error),getString(R.string.confirm_new_password));
                confirmPasswordView.setVisibleError(getView(),false);
            }
        }
    };

    Observer<Boolean> observerLoading = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            loadingView.setLoadingView(getView(),aBoolean);
        }
    };



    public void launchNewPassworod(View view) {

        this.setView(view);
        int tokenSize = tokenView.getLength();
        String token = tokenView.getEditText();
        String password=newPasswordView.getEditText();
        String confirmPassword = confirmPasswordView.getEditText();

        newPasswordObj = new NewPasswordObj();

        newPasswordObj.setEmail(email);
        newPasswordObj.setConfirmationToken(token);
        newPasswordObj.setNewPassword(password);
        newPasswordObj.setNewPasswordConfirmation(confirmPassword);

        newPasswordViewModel.init(newPasswordObj);

        newPasswordViewModel.getInvalidToken().observe(this,observerInvalidToken);
        newPasswordViewModel.getInvalidPassword().observe(this, observerInvalidPassword);
        newPasswordViewModel.getStatus().observe(this,observerState);
        newPasswordViewModel.getLoading().observe(this,observerLoading);

        passwordLoginIntent = new Intent(this, PasswordLoginActivity.class);



    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadingView.setLoadingView(getView(),false);
    }

    public void back_activity(View view) { this.finish(); }

    public void lauchLink(View view) {
    }
}
