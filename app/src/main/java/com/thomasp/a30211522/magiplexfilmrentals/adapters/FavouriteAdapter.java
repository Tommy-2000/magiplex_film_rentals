package com.thomasp.a30211522.magiplexfilmrentals.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.thomasp.a30211522.magiplexfilmrentals.model.MyFavourites_Data;
import com.thomasp.a30211522.magiplexfilmrentals.R;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavFilmViewHolder> {

    private Context fContext;
    private List<MyFavourites_Data> myFavourites_data;
    private RequestOptions option;

    public FavouriteAdapter(List<MyFavourites_Data> myFavourites_data, Context fContext) {
        this.myFavourites_data = myFavourites_data;
        this.fContext = fContext;
    }

    @NonNull
    @Override
    public FavFilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_cardview, viewGroup, false);
        return new FavFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavFilmViewHolder favFilmViewHolder, int i) {
        MyFavourites_Data fl = myFavourites_data.get(i);
        Glide.with(fContext).load(fl.getFilmCertificate()).into(favFilmViewHolder.FilmThumbnail);
        favFilmViewHolder.FilmTitle.setText(fl.getFilmTitle());
        favFilmViewHolder.FilmGenre.setText(fl.getFilmGenre());
        Glide.with(fContext).load(fl.getFilmCertificate()).into(favFilmViewHolder.FilmCertificate);
    }

    @Override
    public int getItemCount() {
        return myFavourites_data.size();
    }

    class FavFilmViewHolder extends RecyclerView.ViewHolder {
        ImageView FilmThumbnail;
        TextView FilmTitle;
        TextView FilmGenre;
        ImageView FilmCertificate;

        FavFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            FilmThumbnail = itemView.findViewById(R.id.film_thumbnail_id);
            FilmTitle = itemView.findViewById(R.id.film_title_id);
            FilmGenre = itemView.findViewById(R.id.film_genre_id);
            FilmCertificate = itemView.findViewById(R.id.film_certificate_id);
        }
    }

}
