package com.example.admin.etest;


import com.example.admin.etest.di.component.AppComponent;
import com.example.admin.etest.di.component.DaggerAppComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class DocumentApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}
