package com.practice.collectionsandmaps;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.practice.collectionsandmaps.fragments.CollectionsAndMapsFragment;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public static final int COLLECTION = 0;
    public static final int MAP = 1;

    private CollectionsAndMapsFragment fragmentForCollections;
    private CollectionsAndMapsFragment fragmentForMaps;

    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0:
                if(fragmentForCollections == null){
                    fragmentForCollections = new CollectionsAndMapsFragment();
                    fragmentForCollections.setIndication(COLLECTION);
                    return fragmentForCollections;
                } else return fragmentForCollections;
            case 1:
                if(fragmentForMaps == null){
                    fragmentForMaps = new CollectionsAndMapsFragment();
                    fragmentForMaps.setIndication(MAP);
                    return fragmentForMaps;
                } else return fragmentForMaps;
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
