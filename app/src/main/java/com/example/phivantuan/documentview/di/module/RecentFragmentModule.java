package com.example.phivantuan.documentview.di.module;

import com.example.phivantuan.documentview.adapter.FileAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class RecentFragmentModule {

    @Provides
    FileAdapter providesAdapter(){
        return new FileAdapter();
    }
}
