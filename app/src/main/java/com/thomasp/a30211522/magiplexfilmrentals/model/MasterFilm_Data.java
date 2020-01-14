package com.thomasp.a30211522.magiplexfilmrentals.model;


public class MasterFilm_Data {

    private int filmId;
    private String filmTitle;
    private String genre;
    private String description;
    private String userRating;
    private String certificate;
    private String thumbnailURL;

    public MasterFilm_Data() {
    }


    public MasterFilm_Data(int filmId, String filmTitle, String genre, String description, String userRating, String certificate, String thumbnailURL) {
        this.filmId = filmId;
        this.filmTitle = filmTitle;
        this.genre = genre;
        this.description = description;
        this.userRating = userRating;
        this.certificate = certificate;
        this.thumbnailURL = thumbnailURL;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return filmTitle;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getCertificate() {
        return certificate;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }


    public void setFilmId(int Id) {
        this.filmId = Id;
    }

    public void setTitle(String title) {
        this.filmTitle = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
