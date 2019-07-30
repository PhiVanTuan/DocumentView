package com.example.phivantuan.documentview;

import android.arch.lifecycle.Observer;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.phivantuan.documentview.adapter.DocumentPagerAdapter;
import com.example.phivantuan.documentview.base.BaseInjectActivity;
import com.example.phivantuan.documentview.databinding.ActivityMainBinding;
import com.example.phivantuan.documentview.db.DocumentDatabase;
import com.example.phivantuan.documentview.model.Office;
import com.example.phivantuan.documentview.msoffice.InstallQbsdk;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseInjectActivity<ActivityMainBinding> {

    private static final int REQUEST_STORAGE_CODE = 212;

    @Inject
    DocumentPagerAdapter adapter;

    @Inject
    DocumentDatabase database;

    @Inject
    InstallQbsdk qbsdk;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    @Override
    protected void initView() {
        initPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE_CODE) {
            initPermission();
        }
    }

    @Override
    protected void onResume() {
          if (checkPermission()&&!qbsdk.isInstalling()){
              qbsdk.install();
          }
        super.onResume();
    }

    private void initPermission() {
        if (checkPermission()) {
            getViewDataBinding().pagerInfo.setAdapter(adapter);
            getViewDataBinding().tabLayoutInfo.setupWithViewPager(getViewDataBinding().pagerInfo);
            getViewDataBinding().pagerInfo.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(getViewDataBinding().tabLayoutInfo));
            qbsdk.install();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, REQUEST_STORAGE_CODE);
        }
    }
}
