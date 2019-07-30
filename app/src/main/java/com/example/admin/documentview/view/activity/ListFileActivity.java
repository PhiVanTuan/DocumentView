package com.example.admin.etest.view.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.admin.etest.R;
import com.example.admin.etest.adapter.FileAdapter;
import com.example.admin.etest.base.BaseActivity;
import com.example.admin.etest.base.BaseRecycleAdapter;
import com.example.admin.etest.db.DocumentDatabase;
import com.example.admin.etest.model.Home;
import com.example.admin.etest.model.Office;
import com.example.admin.etest.viewmodel.ListFileViewModel;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;


public class ListFileActivity extends BaseActivity implements BaseRecycleAdapter.ItemClickListener {
    @Inject
    FileAdapter adapter;

    @Inject
    DocumentDatabase database;
    private Home home;
    private ListFileViewModel viewModel;

    @BindView(R.id.rcView)
    RecyclerView rcView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_list;
    }

    @Override
    protected void initData() {

        viewModel=ViewModelProviders.of(this).get(ListFileViewModel.class);
        viewModel.getLstFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()),home).subscribe(offices ->loadData(offices) ,throwable ->onError(throwable) );


    }

    private void loadData(List<Office> offices){
        progressBar.setVisibility(View.GONE);
        adapter.loadData(offices);
    }

    private void onError(Throwable throwable){
        Log.e("error",throwable.getMessage());
    }
    @Override
    protected void initView() {
        progressBar.setVisibility(View.VISIBLE);
        home= (Home) getIntent().getSerializableExtra("home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("View "+home.getName().toUpperCase());
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rcView.setAdapter(adapter);
        adapter.setItemClickListener(this);
    }

    public static void startListFile(Context context, Home home) {
        Intent intent=new Intent(context,ListFileActivity.class);
        intent.putExtra("home",home);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        viewModel.onItemClick(adapter.getItem(position),database);
    }
}
