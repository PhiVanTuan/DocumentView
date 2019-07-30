package com.example.phivantuan.documentview.viewmodel;

import android.content.Intent;

import com.example.phivantuan.documentview.base.BaseViewModel;
import com.example.phivantuan.documentview.event.ShowFile;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */

public class PdfViewModel extends BaseViewModel  {


    public void showFile(Intent intent,ShowFile showFile){
        if (intent!=null){
            if (intent.getAction().equals("android.intent.action.VIEW")) {
                showFile.showFileFromUri(intent);
            } else {
                showFile.showFileFromApp(intent);
            }
        }
    }

}
