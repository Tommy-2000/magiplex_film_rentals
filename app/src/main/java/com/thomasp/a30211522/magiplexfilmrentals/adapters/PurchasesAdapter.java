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
import com.thomasp.a30211522.magiplexfilmrentals.R;
import com.thomasp.a30211522.magiplexfilmrentals.model.MyPurchases_Data;

import java.util.List;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.FavFilmViewHolder> {

    private Context fContext;
    private List<MyPurchases_Data> myPurchases_data;
    private RequestOptions option;

    public PurchasesAdapter(List<MyPurchases_Data> myPurchases_data, Context fContext) {
        this.myPurchases_data = myPurchases_data;
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
        MyPurchases_Data fl = myPurchases_data.get(i);
        Glide.with(fContext).load(fl.getFilmCertificate()).into(favFilmViewHolder.FilmThumbnail);
        favFilmViewHolder.FilmTitle.setText(fl.getFilmTitle());
        favFilmViewHolder.FilmGenre.setText(fl.getFilmGenre());
        Glide.with(fContext).load(fl.getFilmCertificate()).into(favFilmViewHolder.FilmCertificate);
    }

    @Override
    public int getItemCount() {
        return myPurchases_data.size();
    }

    private class FavFilmViewHolder extends RecyclerView.ViewHolder {
        ImageView FilmThumbnail;
        TextView FilmTitle;
        TextView FilmGenre;
        ImageView FilmCertificate;

        private FavFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            FilmThumbnail = itemView.findViewById(R.id.film_thumbnail_id);
            FilmTitle = itemView.findViewById(R.id.film_title_id);
            FilmGenre = itemView.findViewById(R.id.film_genre_id);
            FilmCertificate = itemView.findViewById(R.id.film_certificate_id);
        }
    }

}
