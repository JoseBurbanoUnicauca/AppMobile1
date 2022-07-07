package edu.unicauca.tindu;

import android.content.Context;

import androidx.room.Room;

public class TinduDBDatabaseAccesor {
    private static TinduDBDatabase TinduDBDatabaseInstance;
    //Constant about the name assigned to SQLite database
    private static final String TINDU_DB_NAME = "tindu_db";

    private TinduDBDatabaseAccesor() {
    }

    public static TinduDBDatabase getInstance(Context context) {
        if (TinduDBDatabaseInstance == null) {
// Create or open a new SQLite database, and return it as a Room Database instance.
            TinduDBDatabaseInstance = Room.databaseBuilder(context,
                    TinduDBDatabase.class, TINDU_DB_NAME).build();
        }
        return TinduDBDatabaseInstance;
    }

}
