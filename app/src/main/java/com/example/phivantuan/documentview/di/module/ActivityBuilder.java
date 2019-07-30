package com.example.phivantuan.documentview.di.module;


import com.example.phivantuan.documentview.MainActivity;
import com.example.phivantuan.documentview.view.activity.ListFileActivity;
import com.example.phivantuan.documentview.view.fragment.HomeFragment;
import com.example.phivantuan.documentview.view.fragment.RecentFragment;

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
