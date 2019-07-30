package com.example.admin.etest.di.module;

import com.example.admin.etest.adapter.HomeAdapter;
import com.example.admin.etest.model.Home;

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
