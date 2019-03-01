package com.example.android.movieapp.loginScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movieapp.R;
import com.example.android.movieapp.viewModel.SignUpContinueViewModel;

public class SignUpContinueActivity extends AppCompatActivity {

    private TextView emailView;
    private EditText tokenEdit;
    private NewAccountError tokenView;
    private View view;
    private Loading loadingView;
    private AlertDialog alerta;
    AlertDialog.Builder builder;

    private Intent passwordLoginIntent;

    private SignUpContinueViewModel signUpContinueViewModel;
    private String email,token;

    public final static String EMAIL_MESSAGE =
            "com.example.mysampleapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_continue);

        emailView = (TextView) findViewById(R.id.email_continue);
        loadingView = (Loading) findViewById(R.id.loading_sign_up_continue);
        tokenView = (NewAccountError) findViewById(R.id.code_continue);

        tokenView.setValue(getString(R.string.invalid_token), getString(R.string.code_hint));
        Intent intent = getIntent();
        email = intent.getStringExtra(LoginActivity.EMAIL_MESSAGE);
        emailView.setText(email);

        signUpContinueViewModel= ViewModelProviders.of(this).get(SignUpContinueViewModel.class);

        builder = new AlertDialog.Builder(this);

    }

    Observer<String> observerStatus = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String status) {


            if ((status).equals("Ok")) {
                passwordLoginIntent.putExtra(EMAIL_MESSAGE, email);
                startActivity(passwordLoginIntent);

            } else if ((status).equals("Token de validação incorreto")){

                tokenView.setValue(getString(R.string.incorrect_token),getString(R.string.validation_token));
                tokenView.setVisibleError(getView(),false);
            } else {
                builder.setTitle("Erro");
                //define a mensagem
                builder.setMessage(status);

                alerta = builder.create();
                alerta.show();
            }

        }
    };

    Observer<Boolean> observerTokenValidation = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean bool) {

            tokenView.setVisibleError(getView(),bool);

        }
    };

    Observer<Boolean> observerLoading = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            loadingView.setLoadingView(getView(),aBoolean);
        }
    };

    public void launchSignUpContinue(View view) {

        token = tokenView.getEditText();
        this.signUpContinueViewModel.init(email,token);
        this.setView(view);

        System.out.println("TOKEEEEEEEEEEEEN");
        signUpContinueViewModel.getStatus().observe(this, observerStatus);
        signUpContinueViewModel.getTokenValidation().observe(this,observerTokenValidation);
        signUpContinueViewModel.getLoading().observe(this,observerLoading);

        passwordLoginIntent = new Intent(this, PasswordLoginActivity.class);
        System.out.println(token);

    }

    public void lauchLink(View view) {

        signUpContinueViewModel.initSendAgain(email);
        Toast toast = Toast.makeText(this, "Tokem enviado com sucesso", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

    }

    public View getView(){
        return this.view;
    }

    public void setView(View v){
        this.view = v;
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadingView.setLoadingView(getView(),false);
    }

    public void back_activity(View view) {this.finish();
    }
}
