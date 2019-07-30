package com.example.phivantuan.documentview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    protected abstract int getLayoutRes();

    protected abstract void initData();

    protected abstract void initView();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());
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
