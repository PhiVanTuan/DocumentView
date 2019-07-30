package com.example.admin.etest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.RoomDatabase;

import com.example.admin.etest.dao.RecentDao;
import com.example.admin.etest.model.Office;

@Database(entities = Office.class,version = 2)
public abstract class DocumentDatabase extends RoomDatabase {

    public abstract RecentDao getRecentDao();
}
