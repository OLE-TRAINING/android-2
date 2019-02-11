package com.example.android.movieapp.loginScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.android.movieapp.R;
import com.example.android.movieapp.connect.RetrofitConfig;
import com.example.android.movieapp.connect.User;

import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        emailView.setValue(getString(R.string.invalid_email), getString(R.string.email_hint));


    }

    public static boolean validarEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void validarEmail(View view) {

        final Intent passwordLoginIntent = new Intent(this, PasswordLogin.class);
        final Intent newAccountIntent = new Intent(this, NewAccount.class);
        email = emailView.getEditText();


        if (validarEmail(email)) {

        } else {

            emailView.setValue(getString(R.string.invalid_email), getString(R.string.example_email));

            emailView.setVisibleError(view);

            emailView.setText();

        }

        Call<User> call = new RetrofitConfig().getEmailService().buscarEmail(email);
        System.out.println(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user!=null) {
                    System.out.println(user.getRegistrationStatus());
                    System.out.println("ENTROOOOOOOOOOOU NO IFFF");
                    if(new String(user.getRegistrationStatus()).equals("REGISTERED")){

                        startActivity(passwordLoginIntent);
                    }

                    else if(new String(user.getRegistrationStatus()).equals("PENDING")){
                        startActivity(newAccountIntent);
                    }
                }

                else{
                    System.out.println("NAO ENTROOOOOU NO IFF");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("CEPService   ", "Erro ao buscar o cep:" + t.getMessage());
            }
        });
    }

    
}
