package com.example.phivantuan.documentview.viewmodel;

import android.content.Intent;

import com.example.phivantuan.documentview.base.BaseViewModel;
import com.example.phivantuan.documentview.event.ShowFile;
import com.example.phivantuan.documentview.util.Const;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */

public class MSOfficeViewModel extends BaseViewModel  {

    public void showFile(Intent intent,ShowFile showFile){
        if (intent!=null){
            if (intent.getAction().equals(Const.ACTION_OPEN_FILE_FROM_APP)) {
                showFile.showFileFromApp(intent);
            }else {
                showFile.showFileFromUri(intent);
            }

        }
    }
}
