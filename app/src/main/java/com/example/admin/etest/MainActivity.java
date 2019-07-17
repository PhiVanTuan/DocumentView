package com.example.admin.etest;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.util.Log;

import com.example.admin.etest.adapter.DocumentPagerAdapter;
import com.example.admin.etest.base.BaseActivity;
import com.example.admin.etest.databinding.ActivityMainBinding;
import com.example.admin.etest.db.DocumentDatabase;
import com.example.admin.etest.model.Office;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

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
        getViewDataBinding().pagerInfo.setAdapter(adapter);
        getViewDataBinding().tabLayoutInfo.setupWithViewPager(getViewDataBinding().pagerInfo);
        getViewDataBinding().pagerInfo.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(getViewDataBinding().tabLayoutInfo));
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

    }


}
