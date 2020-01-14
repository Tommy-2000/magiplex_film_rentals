package com.thomasp.a30211522.magiplexfilmrentals.model;

import androidx.room.Room;

import android.content.Context;

public class DatabaseClient {

    private Context DBContext;
    private static DatabaseClient DBInstance;

    //
    private MagiPlexFilm_DB magiPlexFilmDb;

    private DatabaseClient(Context DBContext) {
        this.DBContext = DBContext;

        //
        magiPlexFilmDb = Room.databaseBuilder(DBContext, MagiPlexFilm_DB.class, "MagiPlexDB").build();
    }

    public static synchronized DatabaseClient getInstance(Context DBContext) {
        if (DBInstance == null) {
            DBInstance = new DatabaseClient(DBContext);
        }
        return DBInstance;
    }

    public MagiPlexFilm_DB getMagiPlexFilmDb() {
        return magiPlexFilmDb;
    }

}
