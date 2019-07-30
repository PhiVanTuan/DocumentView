package com.example.phivantuan.documentview.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.phivantuan.documentview.model.Office;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface RecentDao {
    @Query("Select *from recent order by time desc limit 20")
    LiveData<List<Office>> getRecent();

    @Insert
    void insert(Office office);

    @Update
    int update(Office office);

    @Delete
    int delete(Office office);

    @Query("Select *from recent where path = :path")
    Single<Office> checkRecent(String path);

    @Query("Select *from recent")
    LiveData<List<Office>> getAll();
}
