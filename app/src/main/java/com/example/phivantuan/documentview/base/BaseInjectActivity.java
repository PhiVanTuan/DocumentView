package com.example.phivantuan.documentview.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Phi Van Tuan on 7/25/2019.
 */


public abstract class BaseInjectActivity<T extends ViewDataBinding> extends DaggerAppCompatActivity {
    private T viewDataBinding;

    public T getViewDataBinding(){
        return viewDataBinding;
    }

    protected abstract int getLayoutRes();

    protected abstract void initData();

    protected abstract void initView();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        ButterKnife.bind(this);
        initView();

        initData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
