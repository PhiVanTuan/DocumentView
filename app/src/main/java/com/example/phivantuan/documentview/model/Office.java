package com.example.phivantuan.documentview.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

import com.example.phivantuan.documentview.R;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "recent")
public class Office implements Serializable  {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "path")
    private String path;

    @ColumnInfo(name = "time")
    private long time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIconFile() {
        if (!TextUtils.isEmpty(path)) {
            if (path.endsWith(".doc") || path.endsWith(".docx")) {
                return R.drawable.docx;
            } else if (path.endsWith(".pdf")) {
                return R.drawable.pdf;
            } else if (path.endsWith(".pptx") || path.endsWith(".ppt")) {
                return R.drawable.pptx;
            } else if (path.endsWith(".txt")) {
                return R.drawable.txt;
            } else if (path.endsWith(".html")) {
                return R.drawable.html;
            } else {
                return R.drawable.xlxs;
            }
        }
        return R.drawable.txt;
    }

    public String getName() {
        File file = new File(path);
        if (file.exists()) {
            return file.getName();
        }
        return "";
    }

    public String getLastTimeOpen(){
        if (time!=0){
            String dateString = new SimpleDateFormat("hh:mm MM/dd/yyyy").format(new Date(time));
            return dateString;
        }
        return null;

    }


}
