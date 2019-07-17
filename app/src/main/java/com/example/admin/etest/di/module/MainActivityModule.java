package com.example.admin.etest.di.module;

import android.support.v4.view.PagerAdapter;

import com.example.admin.etest.MainActivity;
import com.example.admin.etest.adapter.DocumentPagerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    DocumentPagerAdapter provideAdapter(MainActivity mainActivity){
        return new DocumentPagerAdapter(mainActivity.getSupportFragmentManager());
    }
}
