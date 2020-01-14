package com.thomasp.a30211522.magiplexfilmrentals.adapters;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.thomasp.a30211522.magiplexfilmrentals.activities.FilmDetailsActivity;
import com.thomasp.a30211522.magiplexfilmrentals.activities.LatestFilmsFragment;
import com.thomasp.a30211522.magiplexfilmrentals.R;
import com.thomasp.a30211522.magiplexfilmrentals.model.MasterFilm_Data;
import com.thomasp.a30211522.magiplexfilmrentals.model.MyFavourites_Data;

import java.util.List;

public class MasterFilmAdapter extends RecyclerView.Adapter<MasterFilmAdapter.FilmViewHolder> {

    private List<MasterFilm_Data> filmDataList;

    private Context fContext;

    private RequestOptions option;
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }


    public MasterFilmAdapter(Context fContext, List<MasterFilm_Data> fDataList) {
        this.fContext = fContext;
        this.filmDataList = fDataList;

        //Request option for Glide functionality
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_frame).error(R.drawable.loading_frame);

    }


    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Set an inflater for the ViewHolder with the film_cardview layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_cardview, parent, false);
        final FilmViewHolder filmViewHolder = new FilmViewHolder(view);


        //This viewholder contains a context that sends an intent to navigate to the FilmDetailsActivity.class
        filmViewHolder.film_context.setOnClickListener(new View.OnClickListener() {
            @java.lang.Override
            public void onClick(View view) {
                Intent intent = new Intent(fContext, FilmDetailsActivity.class);
                intent.putExtra("film_title", filmDataList.get(filmViewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("film_description", filmDataList.get(filmViewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("film_genre", filmDataList.get(filmViewHolder.getAdapterPosition()).getGenre());
                intent.putExtra("film_userrating", filmDataList.get(filmViewHolder.getAdapterPosition()).getUserRating());
                intent.putExtra("img_film_certificate", filmDataList.get(filmViewHolder.getAdapterPosition()).getCertificate());
            }
        });


        //Return the ViewHolder
        return new FilmViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final FilmViewHolder filmViewHolder, int position) {


        MasterFilm_Data masterFilm_data = filmDataList.get(position);
        //Set the positioning of the title and genre data from the MasterFilm_Data to the ViewHolder
        filmViewHolder.tv_film_title.setText(filmDataList.get(position).getTitle());
        filmViewHolder.tv_film_description.setText(filmDataList.get(position).getDescription());
        filmViewHolder.tv_film_userrating.setText(filmDataList.get(position).getUserRating());
        filmViewHolder.tv_film_genre.setText(filmDataList.get(position).getGenre());

        //Load image from the internet and set it as a string linked to an ImageView class using Glide
        String posterThumbnail = "https://image.tmdb.org/t/p/w500" + filmDataList.get(position).getThumbnailURL();
        Glide.with(fContext).load(posterThumbnail).placeholder(R.drawable.loading_frame).into(filmViewHolder.img_film_thumbnail);
        Glide.with(fContext).load(filmDataList.get(position).getCertificate()).apply(option).into(filmViewHolder.img_film_certificate);


        if (LatestFilmsFragment.magiPlexFilm_db.myFavouritesDao().isFavourite(masterFilm_data.getFilmId()) == 1)
            filmViewHolder.iconFavourite.setImageResource(R.drawable.ic_star_24dp);
        else
            filmViewHolder.iconFavourite.setImageResource(R.drawable.ic_star_border_24dp);


        filmViewHolder.iconFavourite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MyFavourites_Data myFavourites_data = new MyFavourites_Data();

                final MasterFilm_Data masterFilm_data = filmDataList.get(position);

                //Get data from MasterFilm_Data model
                int filmId = masterFilm_data.getFilmId();
                String posterThumbnail = masterFilm_data.getThumbnailURL();
                String filmTitle = masterFilm_data.getTitle();
                //Set data to the following strings and integer in the MyFavourites_Data model
                myFavourites_data.setFilmID(filmId);
                myFavourites_data.setFilmThumbnailURL(posterThumbnail);
                myFavourites_data.setFilmTitle(filmTitle);


                //If the favourites icon is set to full, the DAO sends data to the database using the create_database class
                if (LatestFilmsFragment.magiPlexFilm_db.myFavouritesDao().isFavourite(filmId) != 1) {
                    filmViewHolder.iconFavourite.setImageResource(R.drawable.ic_star_24dp);
                    LatestFilmsFragment.magiPlexFilm_db.myFavouritesDao().addData(myFavourites_data);
                } else {
                    filmViewHolder.iconFavourite.setImageResource(R.drawable.ic_star_border_24dp);
                    LatestFilmsFragment.magiPlexFilm_db.myFavouritesDao().delete(myFavourites_data);
                }

            }
        });
    }

    //Return the number of items from the MasterFilm_Data model
    @Override
    public int getItemCount() {
        return filmDataList.size();
    }


    //Set the variables for the FilmViewHolder to contain the data from the correct R resource to an itemView
    public static class FilmViewHolder extends RecyclerView.ViewHolder {


        TextView tv_film_title;
        TextView tv_film_description;
        TextView tv_film_genre;
        TextView tv_film_userrating;
        ImageView img_film_thumbnail;
        ImageView img_film_certificate;
        ImageView iconFavourite;
        LinearLayout ll_film_container;
        RelativeLayout film_context;


        public FilmViewHolder(View itemView) {
            super(itemView);

            tv_film_title = itemView.findViewById(R.id.film_title_id);
            tv_film_description = itemView.findViewById(R.id.film_description_id);
            tv_film_genre = itemView.findViewById(R.id.film_genre_id);
            tv_film_userrating = itemView.findViewById(R.id.film_user_rating_id);
            img_film_thumbnail = itemView.findViewById(R.id.film_thumbnail_id);
            img_film_certificate = itemView.findViewById(R.id.film_certificate_id);
            ll_film_container = itemView.findViewById(R.id.film_container);
            film_context = itemView.findViewById(R.id.filmContext);
            iconFavourite = itemView.findViewById(R.id.iconFavourite);


        }
    }

}
