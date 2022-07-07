package edu.unicauca.tindu;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TinduDB {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Boolean config;
    private String user;
    private long timestamp;

    public TinduDB(Boolean _config, String user) {
        this(_config, user, java.lang.System.currentTimeMillis());
    }

    public TinduDB(Boolean config, String user, long timestamp) {
        this.config = config;
        this.user = user;
        this.timestamp = timestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }


    public Boolean getConfig() {
        return config;
    }

    public void setConfig(Boolean config) {
        this.config = config;
    }

    public long getTimestamp() {
        return java.lang.System.currentTimeMillis();
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
