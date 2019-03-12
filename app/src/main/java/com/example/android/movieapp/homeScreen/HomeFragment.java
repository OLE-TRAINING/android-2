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

import com.example.android.movieapp.HomeFragmentViewModel;
import com.example.android.movieapp.R;
import com.example.android.movieapp.connect.Genres;
import com.example.android.movieapp.connect.ListGenres;
import com.example.android.movieapp.viewModelLogin.LoginViewModel;

import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends Fragment {

    TextView myAwesomeTextView;
    String t;

    private HomeFragmentViewModel homeFragmentViewModel;
    private TabLayout tabLayout;

    private final LinkedList<MovieItem> movieList = new LinkedList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);


        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);

        homeFragmentViewModel.init();

        tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        // Set the text for each tab.

        tabLayout.addTab(tabLayout.newTab().setText("Lançamentos"));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Action));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Adventure));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Animamtion));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_comedy));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Drama));

        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


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
            int i=0;
            System.out.println("O FRAGMENTO ESTÁ RECEBENDO A LSITA DE GENERO");
            System.out.println(listGenres.getListGenres().get(2).getName());
            tabLayout.addTab(tabLayout.newTab().setText("TAKouda"));
            while(i<6){
                tabLayout.addTab(tabLayout.newTab().setText(listGenres.getListGenres().get(i).getName()));
                 i++;
            }
        }
    };


    public void showText(String text) {

    }


}
