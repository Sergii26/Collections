package com.practice.collectionsandmaps;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.practice.collectionsandmaps.fragments.CollectionsFragment;
import com.practice.collectionsandmaps.fragments.MapsFragment;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0: return new CollectionsFragment();
            case 1: return new MapsFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Collections";
            case 1: return "Maps";
            default: return null;
        }
    }
}
