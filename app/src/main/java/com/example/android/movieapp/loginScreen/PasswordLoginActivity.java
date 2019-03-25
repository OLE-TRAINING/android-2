package com.example.android.movieapp.loginScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.User;
import com.example.android.movieapp.homeScreen.HomeActivity;
import com.example.android.movieapp.viewModelLogin.PasswordLoginViewModel;

public class PasswordLoginActivity extends AppCompatActivity {


    private EditText editPassword;
    private TextView viewEmail;
    private View view;
    private User user;
    private String email;
    private  Toast toast;
    private Loading loadingView;
    private Intent newPasswordIntent;
    private Intent homeIntent;
    private AlertDialog alerta;
    private AlertDialog.Builder builder;

    public final static String NEW_PASSWORD_MESSAGE =
            "com.example.mysampleapp.MESSAGE";

    private PasswordLoginViewModel passwordLoginViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);


        editPassword = (EditText) findViewById(R.id.password_edit_password);
        loadingView = (Loading) findViewById(R.id.loading_password_login);
        viewEmail = (TextView) findViewById(R.id.email_password);

        Intent intent = getIntent();

        email = intent.getStringExtra(LoginActivity.EMAIL_MESSAGE);
        viewEmail.setText(email);

        /*Toast toast = Toast.makeText(this, "Usuário ou senha inválido!", Toast.LENGTH_LONG);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        toast.setGravity(Gravity.TOP|Gravity.TOP, 0, 0);
        v.setBackgroundColor(Color.RED);
        toast.show();*/

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Usuário ou senha inválido!");

        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        passwordLoginViewModel = ViewModelProviders.of(this).get(PasswordLoginViewModel.class);

        builder = new AlertDialog.Builder(this);


    }

    Observer<String> observerStatus = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String status) {
            if((status).equals("Ok")){

                startActivity(homeIntent);
            }
            else if ((status).equals("Senha incorreta")) {
                toast.show();
            }

            else {
                builder.setTitle("Erro");
                builder.setMessage(status);
                alerta = builder.create();
                alerta.show();
            }
        }
    };

    Observer<Boolean> observerLogin = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean bool) {
            loadingView.setLoadingView(getView(),bool);
        }
    };



    public void buttonPasswordLogin(View view) {
        this.setView(view);
        user = new User();
        user.setEmail(email);
        user.setPassword(editPassword.getText().toString());
        passwordLoginViewModel.init(user);

        String password = editPassword.getText().toString();

        passwordLoginViewModel.getStatus().observe(this,observerStatus);
        passwordLoginViewModel.getLoading().observe(this,observerLogin);

        homeIntent = new Intent(this, HomeActivity.class);


    }

    public void lauchLink(View view) {

        passwordLoginViewModel.initSendToken(email);
        newPasswordIntent = new Intent(this, NewPasswordActivity.class);
        newPasswordIntent.putExtra(NEW_PASSWORD_MESSAGE, email);
        startActivity(newPasswordIntent);
        Toast toast = Toast.makeText(this, "Clique Novamente", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);


        passwordLoginViewModel.getLoading().observe(this,observerLogin);

    }

    public void back_activity(View view) {this.finish();}

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
}
