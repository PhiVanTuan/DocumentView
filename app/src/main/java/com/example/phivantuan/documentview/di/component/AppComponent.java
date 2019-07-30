package com.example.phivantuan.documentview.di.component;

import android.app.Application;

import com.example.phivantuan.documentview.DocumentApplication;
import com.example.phivantuan.documentview.di.module.ActivityBuilder;

import com.example.phivantuan.documentview.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,AppModule.class, ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(DocumentApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
