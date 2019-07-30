package com.example.phivantuan.documentview.di.module;

import com.example.phivantuan.documentview.adapter.HomeAdapter;
import com.example.phivantuan.documentview.model.Home;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {
    @Provides
    HomeAdapter getAdapter() {
        return new HomeAdapter();
    }

    @Provides
    List<Home> getListHome(){
       return new Home().getListHome();
    }

}
