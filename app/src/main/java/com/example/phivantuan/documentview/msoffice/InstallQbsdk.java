package com.example.phivantuan.documentview.msoffice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;


/**
 * Created by Phi Van Tuan on 30/07/2019
 */

public class InstallQbsdk {
    private Context context;
    private boolean isInstalling = false;

    private Dialog dialog;

    private ProgressDialog pd;

    public InstallQbsdk(Context context2) {
        this.context = context2;
        dialog = new AlertDialog.Builder(context2).setCancelable(false).setMessage("Please set up network connection to install microsoft office sdk").setTitle("No internet connection").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent("android.settings.WIFI_SETTINGS");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context2.startActivity(intent);
            }
        }).create();
    }

    public boolean isInstalling() {
        return this.isInstalling;
    }

    public void install() {
        if (QbSdk.getTbsVersion(this.context) == 0) {
            if (!isNetworkConnected()) {
                showAlertDialog();
                this.isInstalling = false;
            } else {
                this.pd = new ProgressDialog(this.context);
                this.pd.setCancelable(false);
                this.pd.setMessage("Please wait install microsoft office sdk");
                this.pd.setProgressStyle(1);
                this.pd.setIndeterminate(false);
                this.pd.setMax(100);
                this.pd.setProgress(0);
                this.pd.show();
                this.isInstalling = true;
            }
            QbSdk.setDownloadWithoutWifi(true);
            QbSdk.setTBSInstallingStatus(true);
            QbSdk.reset(this.context);
            QbSdk.setTbsListener(new TbsListener() {
                public void onDownloadFinish(int i) {

                    Log.e("Qbsdk", "onDownloadFinish " + i);
//                    pd.setProgress(100);
//                    pd.setIndeterminate(true);
//                    pd.setMessage("Installing");

                }

                public void onInstallFinish(int i) {
                    Log.e("Qbsdk", "onInstallFinish " + i);
                    Toast.makeText(InstallQbsdk.this.context, "Instal succesful", Toast.LENGTH_SHORT).show();
                    InstallQbsdk.this.pd.dismiss();
                }

                public void onDownloadProgress(int newPercent) {
                    Log.e("Qbsdk", "onDownloadProgress " + newPercent);
                    InstallQbsdk.this.pd.setProgress(newPercent);
                }
            });
            QbSdk.initX5Environment(this.context, null);
            ExceptionHandler.getInstance().initConfig(this.context);
            return;
        }
        QbSdk.initX5Environment(this.context, new QbSdk.PreInitCallback() {
            public void onCoreInitFinished() {
                Log.e("Qbsdk", "onCoreInitFinished");
            }

            public void onViewInitFinished(boolean b) {
                Log.e("Qbsdk", "onViewInitFinished " + b);
            }
        });
    }

    private boolean isNetworkConnected() {
        return ((ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public void showAlertDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog.show();
        }
    }

}
