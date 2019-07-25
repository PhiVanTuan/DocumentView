package com.example.admin.etest.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.admin.etest.base.BaseViewModel;
import com.example.admin.etest.db.DocumentDatabase;
import com.example.admin.etest.model.Home;
import com.example.admin.etest.model.Office;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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

    public void onItemClick(final Office office, final DocumentDatabase database) {
        final Office check = database.getRecentDao().checkRecent(office.getPath()).getValue();
        if (check != null) {
            check.setTime(System.currentTimeMillis());
            Completable.fromAction(() -> database.getRecentDao()
                    .update(check))
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        } else {
            office.setTime(System.currentTimeMillis());
            Completable.fromAction(() -> database.getRecentDao()
                    .insert(office))
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        }

    }

}
