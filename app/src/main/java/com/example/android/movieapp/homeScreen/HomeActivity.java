package com.example.android.movieapp.homeScreen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.connect.RetrofitConfig;
import com.example.android.movieapp.loginScreen.LoginActivity;
import com.example.android.movieapp.model.ListGenres;
import com.example.android.movieapp.model.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    String email;
    private User usuarioIntent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        usuarioIntent = (User) intent.getSerializableExtra(LoginActivity.EMAIL_MESSAGE);
        Fragment fragment = HomeFragment.newInstance(usuarioIntent);


        System.out.println("PRINTANDO EMAAAIL HOME ACTIVY");
        System.out.println(email);

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);
//        titleActionBar = (TextView) findViewById(R.id.titleActionBar);
//        titleActionBar.setText(Html.fromHtml(text));

        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragment).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.icon_home:
                            selectedFragment = HomeFragment.newInstance(usuarioIntent);

                            break;

                        case R.id.icon_favorite:
                            selectedFragment = new FavoriteFragment();
                            break;

                        case R.id.icon_search:
                            selectedFragment = new SearchFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, selectedFragment).commit();

                    return true;
                }
            };


}
