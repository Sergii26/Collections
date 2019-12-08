package com.practice.collectionsandmaps;

import com.practice.collectionsandmaps.activity.MainActivityTest;
import com.practice.collectionsandmaps.fragments.CollectionsFragmentTest;
import com.practice.collectionsandmaps.fragments.MapsFragmentTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MainActivityTest.class, CollectionsFragmentTest.class, MapsFragmentTest.class})
public class AndroidTestSuite {
}
