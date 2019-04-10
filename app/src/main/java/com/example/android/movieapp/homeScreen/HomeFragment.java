package com.example.android.movieapp.homeScreen;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.android.movieapp.ViewModelHome.HomeFragmentViewModel;
import com.example.android.movieapp.R;
import com.example.android.movieapp.model.ListGenres;
import com.example.android.movieapp.model.User;

import java.util.LinkedList;

public class HomeFragment extends Fragment  {

    TextView myAwesomeTextView;
    String t;
    ImageButton navigationButton;



    TextView titleActionBar;
    String text = "<font color=#FFFFFF><b>OT</b></font><font color=#FFFFFF>MOVIES</font>";

    private HomeFragmentViewModel homeFragmentViewModel;
    private NavigationView navigationView;
    private LinearLayoutCompat logoutButton;
    private TabLayout tabLayout;
    private TextView emailView;
    private TextView userView;
    private User user;
    private PagerAdapter adapter;
    private  ViewPager viewPager;


    private final LinkedList<MovieItem> movieList = new LinkedList<>();

    public static HomeFragment newInstance(User user){

        HomeFragment homeFragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putSerializable("user",user);
        homeFragment.setArguments(args);

        return homeFragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            user = (User) bundle.getSerializable("user");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        navigationButton = v.findViewById(R.id.button_navigation);
        navigationView = v.findViewById(R.id.navigation_home);
        logoutButton = v.findViewById(R.id.logout_navigation);
        emailView = v.findViewById(R.id.email_navigation);
        userView = v.findViewById(R.id.user_navigation);


        navigationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                navigationView.setVisibility(View.VISIBLE);
                readBundle(getArguments());
                emailView.setText(user.getEmail());
                userView.setText(user.getUsername());
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("CLICOOOOU NO loguoooooot");

                LogoutDialog cdd = new LogoutDialog(getActivity());
                cdd.show();
            }
        });

        Toolbar myToolbar = (Toolbar) v.findViewById(R.id.my_toolbar);
        titleActionBar = (TextView) v.findViewById(R.id.titleActionBar);
        titleActionBar.setText(Html.fromHtml(text));


//
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);

        homeFragmentViewModel.init();

        tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        // Set the text for each

//        tabLayout.addTab(tabLayout.newTab().setText("Lançamentos"));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Action));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Adventure));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Animamtion));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_comedy));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Drama));

        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) v.findViewById(R.id.pager);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new
                                                   TabLayout.OnTabSelectedListener() {
                                                       @Override
                                                       public void onTabSelected(TabLayout.Tab tab) {
                                                           viewPager.setCurrentItem(tab.getPosition());

                                                       }

                                                       @Override
                                                       public void onTabUnselected(TabLayout.Tab tab) {
                                                       }

                                                       @Override
                                                       public void onTabReselected(TabLayout.Tab tab) {
                                                       }
                                                   });

        homeFragmentViewModel.getListGenre().observe(this, observerValidEmail);

        return v;

    }





    Observer<ListGenres> observerValidEmail = new Observer<ListGenres>() {
        @Override
        public void onChanged(@Nullable ListGenres listGenres) {
            int i =0;
            while(i<listGenres.getListGenres().size()){
                tabLayout.addTab(tabLayout.newTab().setText(listGenres.getListGenres().get(i).getName()));
                i++;
            }

            adapter = new PagerAdapter
                    (getFragmentManager(),listGenres);
            viewPager.setAdapter(adapter);
        }
    };

//    Observer<ListGenres> observerValidEmail = new Observer<ListGenres>() {
//        @Override
//        public void onChanged(@Nullable ListGenres listGenres) {
//           int i =0;
//            while(i<listGenres.getListGenres().size()){
//                tabLayout.addTab(tabLayout.newTab().setText(listGenres.getListGenres().get(i).getName()));
//                i++;
//            }
//
//            adapter = new PagerAdapter
//                    (getFragmentManager(),listGenres);
//            viewPager.setAdapter(adapter);
//        }
//    };


    public void showText(String text) {

    }




}
