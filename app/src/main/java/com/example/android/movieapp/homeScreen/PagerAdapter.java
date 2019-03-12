package com.example.android.movieapp.homeScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int nNumOfTabs;

//    private MovieListFragment release = MovieListFragment.newInstance();
//    private MovieListFragment action = MovieListFragment.newInstance();
//    private MovieListFragment adventure  = MovieListFragment.newInstance();
//    private MovieListFragment animation = MovieListFragment.newInstance();
//    private MovieListFragment comedy  = MovieListFragment.newInstance();
//    private MovieListFragment drama  = MovieListFragment.newInstance();

    PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.nNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return MovieListFragment.newInstance();
            case 1: return MovieListFragment.newInstance();
            case 2: return MovieListFragment.newInstance();
            case 3: return MovieListFragment.newInstance();
            case 4: return MovieListFragment.newInstance();
            case 5: return MovieListFragment.newInstance();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return nNumOfTabs;
    }
}
