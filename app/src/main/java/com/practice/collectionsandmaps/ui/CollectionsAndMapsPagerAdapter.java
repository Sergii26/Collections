package com.practice.collectionsandmaps.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.ui.fragment.CollectionsAndMapsFragment;
import com.practice.collectionsandmaps.ui.fragment.FragmentsIndication;

import java.util.List;



public class CollectionsAndMapsPagerAdapter extends FragmentStatePagerAdapter {

    private CollectionsAndMapsFragment[] fragments;
    private Context context;

    public CollectionsAndMapsPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
        final List<Fragment> fragments = fm.getFragments();
        this.fragments = new CollectionsAndMapsFragment[2];
        if(fragments.isEmpty()){
            this.fragments[0] = CollectionsAndMapsFragment.newInstance(FragmentsIndication.COLLECTION);
            this.fragments[1] = CollectionsAndMapsFragment.newInstance(FragmentsIndication.MAP);
        } else {
            this.fragments[0] = (CollectionsAndMapsFragment)fragments.get(0);
            this.fragments[1] = (CollectionsAndMapsFragment)fragments.get(1);

        }
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0:
                return fragments[0];
            case 1:
                return fragments[1];
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return context.getString(R.string.collection_toggle_title);
            case 1: return context.getString(R.string.map_toggle_title);
            default: return null;
        }
    }
}
