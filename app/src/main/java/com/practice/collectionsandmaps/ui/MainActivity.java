package com.practice.collectionsandmaps.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.practice.collectionsandmaps.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.pager);
        CollectionsAndMapsPagerAdapter collectionsAndMapsPagerAdapter = new CollectionsAndMapsPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(collectionsAndMapsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
