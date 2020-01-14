package com.thomasp.a30211522.magiplexfilmrentals.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface MyFavourites_DAO {
    @Insert
    void addData(MyFavourites_Data myFavouritesData);

    @Query("select * from favouritesList")
    List<MyFavourites_Data> getMyFavouritesData();

    @Query("SELECT EXISTS (SELECT 1 FROM favouritesList WHERE FilmID=:FilmID)")
    int isFavourite(int FilmID);

    @Delete
    void delete(MyFavourites_Data myFavouritesData);
}
