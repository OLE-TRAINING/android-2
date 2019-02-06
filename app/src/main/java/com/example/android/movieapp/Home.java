package com.example.android.movieapp;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    TextView titleActionBar;
    String text = "<font color=#FFFFFF><b>OT</b></font><font color=#FFFFFF>MOVIES</font>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        titleActionBar = (TextView) findViewById(R.id.titleActionBar);
        titleActionBar.setText(Html.fromHtml(text));

        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    android.support.v4.app.Fragment selectedFragment =null;

                    switch (item.getItemId()){
                        case R.id.icon_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.icon_favorite:
                            selectedFragment = new FavoriteFragment();
                            break;

                        case R.id.icon_search:
                            selectedFragment = new SearchFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,selectedFragment).commit();

                    return true;
                }
            };










}
