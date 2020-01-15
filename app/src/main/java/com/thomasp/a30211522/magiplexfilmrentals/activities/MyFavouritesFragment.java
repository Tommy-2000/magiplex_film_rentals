package com.thomasp.a30211522.magiplexfilmrentals.activities;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomasp.a30211522.magiplexfilmrentals.R;
import com.thomasp.a30211522.magiplexfilmrentals.adapters.FavouriteAdapter;
import com.thomasp.a30211522.magiplexfilmrentals.model.MagiPlexFilm_DB;
import com.thomasp.a30211522.magiplexfilmrentals.model.MyFavourites_Data;

import java.util.List;


public class MyFavouritesFragment extends Fragment {


    private List<MyFavourites_Data> MyFavouritesDataList;
    private FavouriteAdapter favouriteAdapter;
    public static MagiPlexFilm_DB magiPlexFilm_db;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;


    private static final int VERTICAL_ITEM_SPACE = 48;


    private Context fContext;
    private View rootView;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "MFparam1";
    private static final String ARG_PARAM2 = "MFparam2";

    private String MFParam1;
    private String MFParam2;

    private OnFragmentInteractionListener mListener;

    public MyFavouritesFragment() {
        // Required empty public constructor for fragment to function on Android
    }


    // TODO: Rename and change types and number of parameters
    public static MyFavouritesFragment newInstance(String MFparam1, String MFparam2) {
        MyFavouritesFragment fragment = new MyFavouritesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, MFparam1);
        args.putString(ARG_PARAM2, MFparam2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            MFParam1 = getArguments().getString(ARG_PARAM1);
            MFParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    //Set the inflater for the rootView for this fragment's root layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_favourites, container, false);

        //Set the recyclerView for the rootView and apply spacing
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.favourite_film_feed);

        //Return the rootView and the getter for MyFavourites_Data
        getFavFilmData();
        return rootView;
    }

    private void getFavFilmData() {
        List<MyFavourites_Data> myFavourites_data = LatestFilmsFragment.magiPlexFilm_db.myFavouritesDao().getMyFavouritesData();

        //Use this recyclerView adapter to render the data to these items and set the items into a grid
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(myFavourites_data, fContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(fContext));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //add ItemDecoration
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));

        recyclerView.setAdapter(favouriteAdapter);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view.findViewById(R.id.fragment_frame);
    }


    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
