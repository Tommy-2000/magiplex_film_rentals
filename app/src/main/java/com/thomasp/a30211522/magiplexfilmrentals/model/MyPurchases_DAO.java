package com.thomasp.a30211522.magiplexfilmrentals.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface MyPurchases_DAO {
    @Insert
    void addData(MyPurchases_Data myPurchasesData);

    @Query("select * from purchasesList")
    List<MyPurchases_Data> getMyPurchasesData();

    @Query("SELECT EXISTS (SELECT 1 FROM purchasesList WHERE FilmID=:FilmID)")
    int isPurchased(int FilmID);

    @Delete
    void delete(MyPurchases_Data myPurchasesData);
}
