package com.example.phivantuan.documentview.msoffice;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Phi Van Tuan on 7/25/2019.
 */


public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static ExceptionHandler handler = new ExceptionHandler();
    private Context context;
    private Thread.UncaughtExceptionHandler defaultHandler;
    private File localErrorSave;
    private File saveSpacePath;
    private StringBuilder sb = new StringBuilder();

    private ExceptionHandler() {
    }

    public static ExceptionHandler getInstance() {
        return handler;
    }

    public void initConfig(Context context2) {
        this.context = context2;
        this.saveSpacePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/007");
        this.localErrorSave = new File(this.saveSpacePath, "error.txt");
        if (!this.saveSpacePath.exists()) {
            this.saveSpacePath.mkdirs();
        }
        if (!this.localErrorSave.exists()) {
            try {
                this.localErrorSave.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread t, Throwable e) {
        writeErrorToLocal(t, e);
        upLoadException(t);
    }

    private void upLoadException(Thread t1) {
    }

    private void writeErrorToLocal(Thread t, Throwable e) {
        try {
            BufferedWriter fos = new BufferedWriter(new FileWriter(this.localErrorSave, true));
            PackageManager packageManager = this.context.getPackageManager();
            this.sb.append("\n----------------------------------------------------------------------------------------\n");
            PackageInfo packageInfo = packageManager.getPackageInfo(this.context.getPackageName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            this.sb.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())) + "<---->" + "包名::" + packageInfo.packageName + "<---->版本名::" + packageInfo.versionName + "<---->版本号::" + packageInfo.versionCode + "\n");
            this.sb.append("手机制造商::" + Build.MANUFACTURER + "\n");
            this.sb.append("手机型号::" + Build.MODEL + "\n");
            this.sb.append("CPU架构::" + Build.CPU_ABI + "\n");
            this.sb.append(e.toString() + "\n");
            StackTraceElement[] trace = e.getStackTrace();
            int length = trace.length;
            for (int i = 0; i < length; i++) {
                this.sb.append("\n\tat " + trace[i]);
            }
            this.sb.append("\n");
            if (Build.VERSION.SDK_INT >= 19) {
                for (Throwable se : e.getSuppressed()) {
                    this.sb.append("\tat " + se.getMessage());
                }
            }
            fos.write(this.sb.toString());
            fos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
            this.defaultHandler.uncaughtException(t, e1);
        } catch (PackageManager.NameNotFoundException e12) {
            e12.printStackTrace();
            this.defaultHandler.uncaughtException(t, e12);
        }
    }
}