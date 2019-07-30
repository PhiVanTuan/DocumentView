package com.example.phivantuan.documentview.di.module;

import com.example.phivantuan.documentview.MainActivity;
import com.example.phivantuan.documentview.adapter.DocumentPagerAdapter;
import com.example.phivantuan.documentview.msoffice.InstallQbsdk;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    DocumentPagerAdapter provideAdapter(MainActivity mainActivity){
        return new DocumentPagerAdapter(mainActivity.getSupportFragmentManager());
    }

    @Provides
    InstallQbsdk provideQbSdk(MainActivity mainActivity){
        return new InstallQbsdk(mainActivity);
    }
}
