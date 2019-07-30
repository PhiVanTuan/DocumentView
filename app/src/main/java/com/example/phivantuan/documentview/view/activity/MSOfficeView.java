package com.example.phivantuan.documentview.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.example.phivantuan.documentview.R;
import com.example.phivantuan.documentview.base.BaseActivity;
import com.example.phivantuan.documentview.event.ShowFile;
import com.example.phivantuan.documentview.model.Office;
import com.example.phivantuan.documentview.msoffice.SuperFileView;
import com.example.phivantuan.documentview.util.Const;
import com.example.phivantuan.documentview.util.PathUtil;
import com.example.phivantuan.documentview.viewmodel.MSOfficeViewModel;

import java.io.File;

import butterknife.BindView;

/**
 * Created by Phi Van Tuan on 7/25/2019.
 */

public class MSOfficeView  extends BaseActivity implements ShowFile, SuperFileView.OnGetFilePathListener {
    @BindView(R.id.mSuperFileView)
    SuperFileView mSuperFileView;

    private MSOfficeViewModel viewModel;

    private String filePath;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_office_view;
    }

    @Override
    protected void initData() {
       viewModel= ViewModelProviders.of(this).get(MSOfficeViewModel.class);
       viewModel.showFile(getIntent(),this);
    }

    @Override
    protected void initView() {
        mSuperFileView.setOnGetFilePathListener(this::onGetFilePath);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mSuperFileView != null) {
            this.mSuperFileView.onStopDisplay();
        }
    }

    private void showFile(String path) {
        this.filePath=path;
        this.mSuperFileView.show();

    }

    @Override
    public void showFileFromUri(Intent intent) {
        String path2 = "";
        Uri uri = intent.getData();
        try {
            path2 = PathUtil.getPath(this, uri);
            if (!TextUtils.isEmpty(path2)) {
                showFile(path2);
            } else {
                String pathMail=PathUtil.getPathFromMail(this,uri);
                if (!TextUtils.isEmpty(pathMail)){
                    showFile(pathMail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showFileFromApp(Intent intent) {
        Office office= (Office) intent.getSerializableExtra("office");
        if (!TextUtils.isEmpty(office.getPath())) {
            filePath=office.getPath();
            setTitle(office.getName());
            this.mSuperFileView.show();
        }

    }

    @Override
    public void onGetFilePath(SuperFileView superFileView2) {
        superFileView2.displayFile(new File(filePath));
    }

    public static void startMSOfficeView(Context context, Office office){
        Intent intent=new Intent(context,MSOfficeView.class);
        intent.putExtra("office",office);
        intent.setAction(Const.ACTION_OPEN_FILE_FROM_APP);
        context.startActivity(intent);
    }
}
