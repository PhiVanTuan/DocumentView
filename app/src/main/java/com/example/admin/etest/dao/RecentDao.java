package com.example.admin.etest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.admin.etest.model.Office;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface RecentDao {
    @Query("Select *from recent order by time desc limit 20")
    LiveData<List<Office>> getRecent();

    @Insert
    Observable void insert(Office office);

    @Update
    void update(Office office);

    @Delete
    void delete(Office office);

    @Query("Select *from recent where path = :path")
    LiveData<Office> checkRecent(String path);
}
