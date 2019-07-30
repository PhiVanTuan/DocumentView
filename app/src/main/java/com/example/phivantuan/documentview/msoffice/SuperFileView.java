package com.example.phivantuan.documentview.msoffice;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */

public class SuperFileView   extends FrameLayout implements TbsReaderView.ReaderCallback {
    private static String TAG = "SuperFileView";
    private Context context;
    private OnGetFilePathListener mOnGetFilePathListener;
    private TbsReaderView mTbsReaderView;
    private int saveTime;

    public interface OnGetFilePathListener {
        void onGetFilePath(SuperFileView superFileView2);
    }

    public SuperFileView(Context context2) {
        this(context2, null, 0);
    }

    public SuperFileView(Context context2, AttributeSet attrs) {
        this(context2, attrs, 0);
    }

    public SuperFileView(Context context2, AttributeSet attrs, int defStyleAttr) {
        super(context2, attrs, defStyleAttr);
        this.saveTime = -1;
        this.mTbsReaderView = new TbsReaderView(context2, this);
        addView(this.mTbsReaderView, new LayoutParams(-1, -1));
        this.context = context2;
    }

    public void setOnGetFilePathListener(OnGetFilePathListener mOnGetFilePathListener2) {
        this.mOnGetFilePathListener = mOnGetFilePathListener2;
    }

    private TbsReaderView getTbsReaderView(Context context2) {
        return new TbsReaderView(context2, this);
    }

    public void displayFile(File mFile) {
        if (mFile == null || TextUtils.isEmpty(mFile.toString())) {
//            TLog.e("文件路径无效！");
            return;
        }
        File bsReaderTempFile = new File("/storage/emulated/0/TbsReaderTemp");
        if (!bsReaderTempFile.exists()) {
//            TLog.d("准备创建/storage/emulated/0/TbsReaderTemp！！");
//            if (!bsReaderTempFile.mkdir()) {
//                TLog.e("创建/storage/emulated/0/TbsReaderTemp失败！！！！！");
//            }
        }
        Bundle localBundle = new Bundle();
//        TLog.d(mFile.toString());
        localBundle.putString(TbsReaderView.KEY_FILE_PATH, mFile.toString());
        localBundle.putString(TbsReaderView.KEY_TEMP_PATH, Environment.getExternalStorageDirectory() + "/"+ "TbsReaderTemp");
        if (this.mTbsReaderView == null) {
            this.mTbsReaderView = getTbsReaderView(this.context);
        }
        if (this.mTbsReaderView.preOpen(getFileType(mFile.toString()), false)) {
            this.mTbsReaderView.openFile(localBundle);
        }
    }

    private String getFileType(String paramString) {
        String str = "";
        if (TextUtils.isEmpty(paramString)) {
            Log.d(TAG, "paramString---->null");
            return str;
        }
        Log.d(TAG, "paramString:" + paramString);
        int i = paramString.lastIndexOf(46);
        if (i <= -1) {
            Log.d(TAG, "i <= -1");
            return str;
        }
        String str2 = paramString.substring(i + 1);
        Log.d(TAG, "paramString.substring(i + 1)------>" + str2);
        return str2;
    }

    public void show() {
        if (this.mOnGetFilePathListener != null) {
            this.mOnGetFilePathListener.onGetFilePath(this);
        }
    }

    public void onCallBackAction(Integer integer, Object o, Object o1) {
//        Log.e("****************************************************" + integer);
    }

    public void onStopDisplay() {
        if (this.mTbsReaderView != null) {
            this.mTbsReaderView.onStop();
        }
    }
}
