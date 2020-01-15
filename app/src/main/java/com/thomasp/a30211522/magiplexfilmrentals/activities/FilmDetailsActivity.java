package com.thomasp.a30211522.magiplexfilmrentals.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.thomasp.a30211522.magiplexfilmrentals.R;

import java.util.Objects;

public class FilmDetailsActivity extends AppCompatActivity {

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
    }
}
