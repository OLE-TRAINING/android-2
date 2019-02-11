package com.example.android.movieapp.homeScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movieapp.R;

public class HomeFragment extends Fragment {

    TextView myAwesomeTextView;
    String t;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        TabLayout tabLayout = (TabLayout)v.findViewById(R.id.tab_layout);
        // Set the text for each tab.

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

        return v;


    }

    public void showText(String text) {


    }
}
