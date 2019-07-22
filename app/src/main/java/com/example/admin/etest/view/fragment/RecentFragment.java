package com.example.admin.etest.view.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.etest.R;
import com.example.admin.etest.adapter.FileAdapter;
import com.example.admin.etest.base.BaseFragment;
import com.example.admin.etest.base.BaseRecycleAdapter;
import com.example.admin.etest.db.DocumentDatabase;
import com.example.admin.etest.model.Office;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecentFragment extends BaseFragment implements BaseRecycleAdapter.ItemClickListener {
    @BindView(R.id.rcView)
    RecyclerView rcView;

    @Inject
    FileAdapter adapter;

    @Inject
    DocumentDatabase database;

    public static RecentFragment newInstance() {

        Bundle args = new Bundle();

        RecentFragment fragment = new RecentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_list;
    }

    @Override
    protected void initData() {
        adapter.setItemClickListener(this);
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        rcView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rcView.setAdapter(adapter);
        database.getRecentDao().getRecent().observe(getActivity(), new Observer<List<Office>>() {
            @Override
            public void onChanged(@Nullable List<Office> offices) {
                adapter.loadData(offices);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        final Office office=adapter.getItem(position);
        if (office!=null&&new File(office.getPath()).exists()){
            office.setTime(System.currentTimeMillis());
            database.getRecentDao().update(office).;
        }else {
            database.getRecentDao().delete(office);
        }
        Observable.just(database).subscribeOn(Schedulers.io()).subscribe(new Consumer<DocumentDatabase>() {
            @Override
            public void accept(DocumentDatabase database) throws Exception {
                if (office!=null&&new File(office.getPath()).exists()){
                    office.setTime(System.currentTimeMillis());
                    database.getRecentDao().update(office);
                }else {
                    database.getRecentDao().delete(office);
                }
            }
        });



    }
}
