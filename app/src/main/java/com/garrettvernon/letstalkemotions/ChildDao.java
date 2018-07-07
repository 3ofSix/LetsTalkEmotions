/*
The Data Access Object for the POJO Child
Support the following annotations for the Room Database
@DAO
@Insert
@Delete
@Query
@Update
 */
package com.garrettvernon.letstalkemotions;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface ChildDao {
    @Insert(onConflict = IGNORE)
    void addChild(Child child);

    @Delete
    void deleteChild (Child child);

    @Query("SELECT * FROM child")
    LiveData<List<Child>> getAllChildren();

    //remove before final app
    @Query("DELETE FROM child")
    void deleteAll();

    @Insert
    void insert(Child child);
}
