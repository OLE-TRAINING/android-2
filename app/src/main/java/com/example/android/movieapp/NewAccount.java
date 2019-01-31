package com.example.android.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewAccount extends AppCompatActivity {

   private NewAccountError nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_account);
        nameView = (NewAccountError) findViewById(R.id.name_new_account);
        nameView.setValue("Nome Inválido","   Nome Completo");
        NewAccountError userView = (NewAccountError) findViewById(R.id.user_new_account);
        userView.setValue("Nome de usuário obrigatório","   Nome de usuário");
        NewAccountError passwordView = (NewAccountError) findViewById(R.id.password_new_account);
        passwordView.setValue("Senha inválida","   Senha");

    }


    public void buttonNewAccount(View view) {

        nameView.setVisible(view);
    }
}
