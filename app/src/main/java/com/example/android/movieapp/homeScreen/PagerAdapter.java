package com.example.android.movieapp.homeScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.movieapp.model.ListGenres;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int nNumOfTabs;
    private ListGenres listGenres;

//    private MovieListFragment release = MovieListFragment.newInstance();
//    private MovieListFragment action = MovieListFragment.newInstance();
//    private MovieListFragment adventure  = MovieListFragment.newInstance();
//    private MovieListFragment animation = MovieListFragment.newInstance();
//    private MovieListFragment comedy  = MovieListFragment.newInstance();
//    private MovieListFragment drama  = MovieListFragment.newInstance();

    PagerAdapter(FragmentManager fm,ListGenres listGenres) {
        super(fm);
        this.listGenres = listGenres;

    }


    @Override
    public Fragment getItem(int position) {
//        switch (position) {
//            case 0: return new MovieListFragment();
//            case 1: return new MovieListFragment();
//            case 2: return new MovieListFragment();
//            case 3: return new MovieListFragment();
//            case 4: return new MovieListFragment();
//            case 5: return new MovieListFragment();
//
//            default: return null;
//        }
        System.out.println("GET ITEMMMM");
        System.out.println(position);
        return MovieListFragment.newInstance(listGenres.getListGenres().get(position).getId());
    }

    @Override
    public int getCount() {
        return listGenres.getListGenres().size();
    }
}
