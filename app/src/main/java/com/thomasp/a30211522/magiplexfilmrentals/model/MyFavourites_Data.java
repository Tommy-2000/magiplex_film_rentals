package com.thomasp.a30211522.magiplexfilmrentals.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "favouritesList")
public class MyFavourites_Data implements Serializable {

    @PrimaryKey
    private int FilmID;

    @ColumnInfo(name = "FilmTitle")
    private String FilmTitle;

    @ColumnInfo(name = "FilmGenre")
    private String FilmGenre;

    @ColumnInfo(name = "FilmDescription")
    private String FilmDescription;

    @ColumnInfo(name = "FilmUserRating")
    private String FilmUserRating;

    @ColumnInfo(name = "FilmCertificate")
    private String FilmCertificate;

    @ColumnInfo(name = "FilmThumbnailURL")
    private String FilmThumbnailURL;


    int getFilmID() {
        return FilmID;
    }

    public void setFilmID(int FilmID) {
        this.FilmID = FilmID;
    }


    public String getFilmTitle() {
        return FilmTitle;
    }

    public void setFilmTitle(String FilmTitle) {
        this.FilmTitle = FilmTitle;
    }


    public String getFilmGenre() {
        return FilmGenre;
    }

    void setFilmGenre(String FilmGenre) {
        this.FilmGenre = FilmGenre;
    }


    String getFilmDescription() {
        return FilmDescription;
    }

    void setFilmDescription(String FilmDescription) {
        this.FilmDescription = FilmDescription;
    }


    String getFilmUserRating() {
        return FilmUserRating;
    }

    void setFilmUserRating(String FilmUserRating) {
        this.FilmUserRating = FilmUserRating;
    }


    public String getFilmCertificate() {
        return FilmCertificate;
    }

    void setFilmCertificate(String FilmCertificate) {
        this.FilmCertificate = FilmCertificate;
    }


    String getFilmThumbnailURL() {
        return FilmThumbnailURL;
    }

    public void setFilmThumbnailURL(String FilmThumbnailURL) {
        this.FilmThumbnailURL = FilmThumbnailURL;
    }

}
