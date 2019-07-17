package com.example.admin.etest.di.module;

import com.example.admin.etest.adapter.FileAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class RecentFragmentModule {

    @Provides
    FileAdapter providesAdapter(){
        return new FileAdapter();
    }
}
