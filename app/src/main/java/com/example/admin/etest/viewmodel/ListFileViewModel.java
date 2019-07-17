package com.example.admin.etest.viewmodel;

import android.arch.lifecycle.LiveData;

import com.example.admin.etest.base.BaseViewModel;
import com.example.admin.etest.db.DocumentDatabase;
import com.example.admin.etest.model.Office;

import java.io.File;
import java.util.List;

public class ListFileViewModel extends BaseViewModel {



    public LiveData<List<Office>> getListFile(File file){
        File[] files;
        for (File inFile : file.listFiles()) {
            if (inFile.isDirectory()) {
                loadFile(inFile, endWith12, endWidth2);
            }
            if (inFile.isFile() && (inFile.getAbsolutePath().endsWith(endWith12) || inFile.getAbsolutePath().endsWith(endWidth2))) {
                this.lstOffice.add(new Office(inFile.getAbsolutePath(), inFile.getName()));
            }
        }

    }

    private boolean isEndWith(File file){

    }

    public void onItemClick(Office office, DocumentDatabase database){
        if ()
    }
}
