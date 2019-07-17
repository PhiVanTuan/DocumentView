package com.example.admin.etest;

import android.arch.lifecycle.Observer;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.admin.etest.adapter.DocumentPagerAdapter;
import com.example.admin.etest.base.BaseActivity;
import com.example.admin.etest.databinding.ActivityMainBinding;
import com.example.admin.etest.db.DocumentDatabase;
import com.example.admin.etest.model.Office;

import java.security.Permission;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final int REQUEST_STORAGE_CODE=212;

    @Inject
    DocumentPagerAdapter adapter;

    @Inject
    DocumentDatabase database;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

        database.getRecentDao().getRecent().observe(this, new Observer<List<Office>>() {
            @Override
            public void onChanged(@Nullable List<Office> offices) {

            }
        });
        database.getRecentDao().getRecent().observeForever(new Observer<List<Office>>() {
            @Override
            public void onChanged(@Nullable List<Office> offices) {
                Log.e("size",offices.size()+"");
            }
        });

    }

    @Override
    protected void initView() {
        initPermission();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_STORAGE_CODE){
             initPermission();
        }
    }

    private void initPermission(){
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED ) {
            getViewDataBinding().pagerInfo.setAdapter(adapter);
            getViewDataBinding().tabLayoutInfo.setupWithViewPager(getViewDataBinding().pagerInfo);
            getViewDataBinding().pagerInfo.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(getViewDataBinding().tabLayoutInfo));
        }else {
            ActivityCompat.requestPermissions(this,new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},REQUEST_STORAGE_CODE);
        }
    }
}
