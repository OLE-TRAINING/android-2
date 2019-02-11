package com.example.android.movieapp.homeScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int nNumOfTabs;
    ReleaseFragment release = ReleaseFragment.newInstance("LANÇAMENTO");
    ReleaseFragment action = ReleaseFragment.newInstance("AÇÃO");
    ReleaseFragment adventure  = ReleaseFragment.newInstance("Aventura");
    ReleaseFragment animation= ReleaseFragment.newInstance("Animação");
    ReleaseFragment comedy  = ReleaseFragment.newInstance("Comédia");
    ReleaseFragment drama  = ReleaseFragment.newInstance("Drama");

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.nNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return release;
            case 1: return action;
            case 2: return adventure;
            case 3: return animation;
            case 4: return comedy;
            case 5: return drama;

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return nNumOfTabs;
    }
}
