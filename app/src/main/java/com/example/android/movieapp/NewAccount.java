package com.example.android.movieapp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewAccount extends AppCompatActivity {

   private New_Account_Error nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_account);
        nameView = (New_Account_Error) findViewById(R.id.nameNewAccount);
        nameView.setValue("Nome Inválido","   Nome Completo");
        New_Account_Error userView = (New_Account_Error) findViewById(R.id.userNewAccount);
        userView.setValue("Nome de usuário obrigatório","   Nome de usuário");
        New_Account_Error passwordView = (New_Account_Error) findViewById(R.id.passwordNewAccount);
        passwordView.setValue("Senha inválida","   Senha");

    }


    public void buttonNewAccount(View view) {

        nameView.setVisible(view);
    }
}
