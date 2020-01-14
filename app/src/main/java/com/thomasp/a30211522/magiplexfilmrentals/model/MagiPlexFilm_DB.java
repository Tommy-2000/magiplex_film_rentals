package com.thomasp.a30211522.magiplexfilmrentals.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {MyFavourites_Data.class, MyPurchases_Data.class}, version = 1, exportSchema = false)
public abstract class MagiPlexFilm_DB extends RoomDatabase {


    public abstract MyFavourites_DAO myFavouritesDao();

    public abstract MyPurchases_DAO myPurchasesDao();

}
