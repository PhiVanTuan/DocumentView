package com.example.phivantuan.documentview.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.phivantuan.documentview.R;
import com.example.phivantuan.documentview.base.BaseActivity;
import com.example.phivantuan.documentview.event.ShowFile;
import com.example.phivantuan.documentview.model.Office;
import com.example.phivantuan.documentview.util.Const;
import com.example.phivantuan.documentview.viewmodel.PdfViewModel;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import java.io.File;

import butterknife.BindView;

/**
 * Created by Phi Van Tuan on 7/25/2019.
 */


public class PdfViewActivity extends BaseActivity implements OnLoadCompleteListener, OnPageChangeListener, ShowFile {
    @BindView(R.id.tvPageNumber)
    TextView tvPageNumber;

    @BindView(R.id.pdfView)
    PDFView pdfView;

    private PdfViewModel viewModel;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pdf_view;
    }

    @Override
    protected void initData() {
        viewModel= ViewModelProviders.of(this).get(PdfViewModel.class);
        viewModel.showFile(getIntent(),this);

    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static void startPdfView(Context context, Office office){
        Intent intent=new Intent(context,PdfViewActivity.class);
        intent.setAction(Const.ACTION_OPEN_FILE_FROM_APP);
        intent.putExtra("office",office);
        context.startActivity(intent);
    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        this.tvPageNumber.setVisibility(View.VISIBLE);
        this.tvPageNumber.setText((page + 1) + "/" + pageCount);

    }

    @Override
    public void showFileFromUri(Intent intent) {
        this.pdfView.fromUri(intent.getData()).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).defaultPage(0).onLoad(this).onPageChange(this).scrollHandle(null).load();
    }

    @Override
    public void showFileFromApp(Intent intent) {
        Office office= (Office) intent.getSerializableExtra("office");
        String title = office.getName();
        this.pdfView.fromFile(new File(office.getPath())).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).defaultPage(0).onLoad(this).onPageChange(this).scrollHandle(null).load();
        setTitle(title);
    }
}
