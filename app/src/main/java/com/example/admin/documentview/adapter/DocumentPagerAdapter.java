package com.example.admin.etest.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.etest.view.fragment.HomeFragment;
import com.example.admin.etest.view.fragment.RecentFragment;

public class DocumentPagerAdapter extends FragmentPagerAdapter {
    public DocumentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return HomeFragment.newInstance();
        }
        return RecentFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0) return "View File";
        return "Recent";

    }
}
