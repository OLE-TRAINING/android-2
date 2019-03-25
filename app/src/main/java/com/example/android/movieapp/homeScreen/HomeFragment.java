package com.example.android.movieapp.homeScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movieapp.ViewModelHome.HomeFragmentViewModel;
import com.example.android.movieapp.R;
import com.example.android.movieapp.model.ListGenres;

import java.util.LinkedList;

public class HomeFragment extends Fragment  {

    TextView myAwesomeTextView;
    String t;

    private HomeFragmentViewModel homeFragmentViewModel;
    private TabLayout tabLayout;
    private PagerAdapter adapter;
    private  ViewPager viewPager;


    private final LinkedList<MovieItem> movieList = new LinkedList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);


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
