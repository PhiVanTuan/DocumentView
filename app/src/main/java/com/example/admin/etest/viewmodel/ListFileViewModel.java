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

public class ListFileViewModel extends BaseViewModel {


   public MutableLiveData<List<Office>> getListFile(File file,Home home){
       MutableLiveData<List<Office>> liveData=new MutableLiveData<>();
       List<Office> lstOffice=new ArrayList<>();
       loadFile(file,home,lstOffice);
       liveData.setValue(lstOffice);
       return liveData;
   }

    private void loadFile(File file,Home home,List<Office> lstOffice){

        for (File inFile : file.listFiles()) {
            if (inFile.isDirectory()) {
                loadFile(inFile, home, lstOffice);
            }
            if (inFile.isFile() && isEndWith(inFile,home)) {
                Office office=new Office();
                office.setPath(inFile.getAbsolutePath());
                lstOffice.add(office);
            }
        }

    }

    private boolean isEndWith(File file, Home home){
         List<String> lstExtension=home.getExtension();
         for (int i=0;i<lstExtension.size();i++){
             if (file.getAbsolutePath().endsWith(lstExtension.get(i))){
                 return true;
             }
         }
         return false;
    }

    public void onItemClick(Office office, DocumentDatabase database){

    }
}
