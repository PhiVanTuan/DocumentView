package com.example.phivantuan.documentview.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.phivantuan.documentview.db.DocumentDatabase;
import com.example.phivantuan.documentview.di.scope.DataBaseInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    Context getContext(Application application){
       return application;
    }

    @Provides
    @DataBaseInfo
    String getDataBaseName(){
        return "Document";
    }


    @Provides
    @Singleton
    DocumentDatabase provideDataBase(@DataBaseInfo String dbName, Context context){
        return Room.databaseBuilder(context, DocumentDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

}
