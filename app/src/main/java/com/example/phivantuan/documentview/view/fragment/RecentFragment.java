package com.example.phivantuan.documentview.view.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.phivantuan.documentview.R;
import com.example.phivantuan.documentview.adapter.FileAdapter;
import com.example.phivantuan.documentview.base.BaseFragment;
import com.example.phivantuan.documentview.base.BaseRecycleAdapter;
import com.example.phivantuan.documentview.db.DocumentDatabase;
import com.example.phivantuan.documentview.model.Office;
import com.example.phivantuan.documentview.view.activity.MSOfficeView;
import com.example.phivantuan.documentview.view.activity.PdfViewActivity;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Completable;
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
        if (office.getPath().endsWith(".pdf")){
            PdfViewActivity.startPdfView(getContext(),office);
        }else if ((office.getPath().endsWith(".xls"))||(office.getPath().endsWith(".xlsx"))||(office.getPath().endsWith(".doc"))||(office.getPath().endsWith(".docx"))||(office.getPath().endsWith(".ppt"))||(office.getPath().endsWith(".pptx"))){
            MSOfficeView.startMSOfficeView(getContext(),office);
        }
        if (office!=null&&new File(office.getPath()).exists()){
            office.setTime(System.currentTimeMillis());
            Completable.fromAction(() -> database.getRecentDao()
                    .update(office))
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        }else {
            Completable.fromAction(() -> database.getRecentDao()
                    .delete(office))
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        }

    }
}
