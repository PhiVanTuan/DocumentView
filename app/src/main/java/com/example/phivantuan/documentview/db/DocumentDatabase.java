package com.example.phivantuan.documentview.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.phivantuan.documentview.dao.RecentDao;
import com.example.phivantuan.documentview.model.Office;

@Database(entities = Office.class,version = 2)
public abstract class DocumentDatabase extends RoomDatabase {

    public abstract RecentDao getRecentDao();
}
