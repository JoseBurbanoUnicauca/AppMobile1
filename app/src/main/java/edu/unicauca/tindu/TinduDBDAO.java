package edu.unicauca.tindu;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TinduDBDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTinduDBs(List<TinduDB> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTinduDB(TinduDB item);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateTinduDBs(List<TinduDB> items);

    @Delete
    public void deleteTinduDB(TinduDB item);

    @Query("SELECT * FROM tindudb ORDER BY id ASC")
    public List<TinduDB> loadAllItems();

    @Query("DELETE FROM tindudb")
    public void deleteAllItems();
}
