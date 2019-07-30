package com.example.admin.etest.di.module;


import com.example.admin.etest.MainActivity;
import com.example.admin.etest.view.activity.ListFileActivity;
import com.example.admin.etest.view.fragment.HomeFragment;
import com.example.admin.etest.view.fragment.RecentFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,HomeFragmentModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {HomeFragmentModule.class})
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector(modules = {RecentFragmentModule.class})
    abstract RecentFragment bindRecentFragment();

    @ContributesAndroidInjector(modules = {ListFileActivityModule.class})
    abstract ListFileActivity bindListFileActivity();

}
