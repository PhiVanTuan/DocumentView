package com.example.phivantuan.documentview.event;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phivantuan.documentview.R;
import com.example.phivantuan.documentview.db.DocumentDatabase;
import com.example.phivantuan.documentview.di.scope.ItemClickInfo;
import com.example.phivantuan.documentview.model.Office;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */

public class ItemClick {
    private Dialog dialog;
    private Context context;
    private Office office;
    @Inject
    DocumentDatabase database;


    public ItemClick(@ItemClickInfo Context context, @ItemClickInfo Office office) {
        this.context = context;
        this.office = office;
    }

    public void showFile() {
        this.dialog = new Dialog(this.context);
        this.dialog.setTitle("File Information");
        this.dialog.setContentView(R.layout.dialog_file_info);
        TextView txtNameFile = (TextView) this.dialog.findViewById(R.id.tvName);
        TextView txtLocationFile = (TextView) this.dialog.findViewById(R.id.tvLocation);
        TextView txtSizeFile = (TextView) this.dialog.findViewById(R.id.tvSize);
        TextView txtLastOpenFile = (TextView) this.dialog.findViewById(R.id.tvLast);
        Button btnOk = (Button) this.dialog.findViewById(R.id.btnOK);
        File f = new File(office.getPath());
        if (f.exists()) {
            txtNameFile.setText("Name : " + f.getName());
            txtLocationFile.setText("Location : " + f.getAbsolutePath().replace(f.getName(), ""));
            float size = (float) (f.length() / 1024);
            if (size < 1024.0f) {
                txtSizeFile.setText("Size : " + BigDecimal.valueOf((double) size).setScale(0, 4).floatValue() + "KB");
            } else {
                txtSizeFile.setText("Size : " + BigDecimal.valueOf((double) (size / 1024.0f)).setScale(1, 4).floatValue() + "MB");
            }
            txtLastOpenFile.setText("Last Modified : " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(f.lastModified())));
            this.dialog.show();
            btnOk.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ItemClick.this.dialog.dismiss();
                }
            });
        } else {
            Toast.makeText(context, "The path of the changed file or file has been deleted.Please check the file", Toast.LENGTH_SHORT).show();
            database.getRecentDao().checkRecent(office.getPath()).subscribeOn(Schedulers.io()).subscribe(office1 -> deleteOffice(office1), throwable -> {
            });
        }


    }

    public void renameFile(){

    }

    private void deleteOffice(Office office) {
        Completable.fromAction(() -> database.getRecentDao()
                .delete(office))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void updateOffice(Office office){
        Completable.fromAction(() -> database.getRecentDao()
                .update(office))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
