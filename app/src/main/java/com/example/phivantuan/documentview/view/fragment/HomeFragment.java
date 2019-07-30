package com.example.phivantuan.documentview.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.phivantuan.documentview.R;
import com.example.phivantuan.documentview.adapter.HomeAdapter;
import com.example.phivantuan.documentview.base.BaseFragment;
import com.example.phivantuan.documentview.base.BaseRecycleAdapter;


import com.example.phivantuan.documentview.model.Home;
import com.example.phivantuan.documentview.view.activity.ListFileActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements BaseRecycleAdapter.ItemClickListener {
    @Inject
    HomeAdapter adapter;

    @BindView(R.id.rcView)
    RecyclerView rcView;

    @Inject
    List<Home> lstHome;



    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.layout_list;
    }

    @Override
    protected void initData() {
        rcView.setLayoutManager(new GridLayoutManager(getContext(),2));
        rcView.setAdapter(adapter);
        adapter.setItemClickListener(this);
        adapter.loadData(lstHome);
    }

    @Override
    public void onItemClick(int position) {
        ListFileActivity.startListFile(getContext(),adapter.getItem(position));
    }
}
