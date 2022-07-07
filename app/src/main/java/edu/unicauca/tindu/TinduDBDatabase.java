package edu.unicauca.tindu;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TinduDB.class}, version = 1)
public abstract class TinduDBDatabase extends RoomDatabase {
    public abstract TinduDBDAO TinduDBDAO();
}
