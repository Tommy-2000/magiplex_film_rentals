package com.thomasp.a30211522.magiplexfilmrentals.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.thomasp.a30211522.magiplexfilmrentals.R;
import com.thomasp.a30211522.magiplexfilmrentals.model.MasterFilm_Data;

import java.util.List;
import java.util.Objects;

public class FilmDetailsActivity extends AppCompatActivity {

    private List<MasterFilm_Data> filmDataList;
    private Context fContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);


        //Hide the default action bar to properly display the nested scroll view
        getSupportActionBar().hide();

        //Receive main film data
        Intent intent = getIntent();
        String filmTitle = getIntent().getExtras().getString("film_title");
        String filmDescription = getIntent().getExtras().getString("film_description");
        String filmGenre = getIntent().getExtras().getString("film_genre");
        String filmUserRating = getIntent().getExtras().getString("film_user_ratings");
        String filmCertificate = getIntent().getExtras().getString("img_film_certificate");
        String filmThumbnailIMG = getIntent().getExtras().getString("img_film_thumbnail");


        //Initialise the views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.filmDetail_collapsing_layout);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tvfilmTitle = findViewById(R.id.film_title_id);
        TextView tvfilmDescription = findViewById(R.id.film_description_id);
        TextView tvfilmGenre = findViewById(R.id.film_genre_id);
        TextView tvfilmUserRating = findViewById(R.id.film_user_rating_id);
        ImageView ivfilmCertificate = findViewById(R.id.film_certificate_id);
        ImageView ivfilmThumbnailIMG = findViewById(R.id.film_thumbnail_id);


        //Setting the values to each view
        tvfilmTitle.setText(filmTitle);
        tvfilmDescription.setText(filmDescription);
        tvfilmGenre.setText(filmGenre);
        tvfilmUserRating.setText(filmUserRating);

    }
}
