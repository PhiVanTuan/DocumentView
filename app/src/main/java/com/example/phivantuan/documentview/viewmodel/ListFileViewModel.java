package com.example.phivantuan.documentview.viewmodel;

import android.content.Context;

import com.example.phivantuan.documentview.base.BaseViewModel;
import com.example.phivantuan.documentview.db.DocumentDatabase;
import com.example.phivantuan.documentview.model.Home;
import com.example.phivantuan.documentview.model.Office;
import com.example.phivantuan.documentview.view.activity.MSOfficeView;
import com.example.phivantuan.documentview.view.activity.PdfViewActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListFileViewModel extends BaseViewModel {

    public Observable<List<Office>> getLstFile(File file, Home home) {
        Observable<List<Office>> observable=Observable.just(file).map(file1 -> getListFile(file,home)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private List<Office> getListFile(File file, Home home) {
        List<Office> lstOffice = new ArrayList<>();
        for (File inFile : file.listFiles()) {
            try {
                if (inFile.isDirectory()) {
                    List<Office> lstDirectory = getListFile(inFile, home);
                    if (lstDirectory != null && lstDirectory.size() > 0) {
                        lstOffice.addAll(lstDirectory);
                    }

                }
                if (inFile.isFile() && isEndWith(inFile, home)) {
                    Office office = new Office();
                    office.setPath(inFile.getAbsolutePath());
                    lstOffice.add(office);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lstOffice;
    }

    private boolean isEndWith(File file, Home home) {
        List<String> lstExtension = home.getExtension();
        for (int i = 0; i < lstExtension.size(); i++) {
            if (file.getAbsolutePath().endsWith(lstExtension.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void onItemClick(Context context,final Office office, final DocumentDatabase database) {
        database.getRecentDao().checkRecent(office.getPath()).subscribeOn(Schedulers.io()).subscribe(office1 -> update(office1,database),throwable ->insert(office,database) );
        if (office.getPath().endsWith(".pdf")){
            PdfViewActivity.startPdfView(context,office);
        }else if ((office.getPath().endsWith(".xls"))||(office.getPath().endsWith(".xlsx"))||(office.getPath().endsWith(".doc"))||(office.getPath().endsWith(".docx"))||(office.getPath().endsWith(".ppt"))||(office.getPath().endsWith(".pptx"))){
            MSOfficeView.startMSOfficeView(context,office);
        }

    }

    private void update(Office office,DocumentDatabase database){
        office.setTime(System.currentTimeMillis());
        Completable.fromAction(() -> database.getRecentDao()
                .update(office))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void insert(Office office,DocumentDatabase database){
        office.setTime(System.currentTimeMillis());
        Completable.fromAction(() -> database.getRecentDao()
                .insert(office))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }


}
