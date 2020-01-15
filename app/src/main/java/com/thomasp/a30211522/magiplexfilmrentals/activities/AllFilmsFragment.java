package com.thomasp.a30211522.magiplexfilmrentals.activities;

import androidx.recyclerview.widget.DefaultItemAnimator;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.thomasp.a30211522.magiplexfilmrentals.R;
import com.thomasp.a30211522.magiplexfilmrentals.adapters.MasterFilmAdapter;
import com.thomasp.a30211522.magiplexfilmrentals.model.MagiPlexFilm_DB;
import com.thomasp.a30211522.magiplexfilmrentals.model.MasterFilm_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllFilmsFragment extends Fragment {


    private static final String JSON_URL = "https://api.themoviedb.org/3/movie/latest?api_key=f86cdcb19008ff7337dfe14255d3139f&language=en-GB";
    private static final String TAG = AllFilmsFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    private List<MasterFilm_Data> FilmDataList = new ArrayList<>();
    private MasterFilmAdapter masterFilmAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;



    private static final int VERTICAL_ITEM_SPACE = 48;

    private View rootView;
    private Context fContext;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String AFParam1;
    private String AFParam2;

    private OnFragmentInteractionListener mListener;

    public AllFilmsFragment() {
        // Required empty public constructor
    }

    public static AllFilmsFragment newInstance(String AF_param1, String AF_param2) {
        AllFilmsFragment fragment = new AllFilmsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, AF_param1);
        args.putString(ARG_PARAM2, AF_param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            AFParam1 = getArguments().getString(ARG_PARAM1);
            AFParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    //Set the inflater for the rootView for this fragment's root layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_all_films, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.film_feed);


        progressDialog = new ProgressDialog(getActivity());
        //Show progress dialog before making http request
        progressDialog.setMessage("Retrieving...");
        progressDialog.show();


        JsonArrayRequest filmJSONRequest = new JsonArrayRequest(JSON_URL, response -> {
            Log.d(TAG, response.toString());
            hidePDialog();

            for (int i = 0; i < response.length(); i++) {

                try {
                    JSONObject object = response.getJSONObject(i);
                    MasterFilm_Data film_data = new MasterFilm_Data();
                    film_data.setFilmId(object.getInt("imdb_id"));
                    film_data.setTitle(object.getString("original_title"));
                    film_data.setGenre(object.getString("genres"));
                    film_data.setDescription(object.getString("overview"));
                    film_data.setUserRating(object.getString("vote_average"));
                    film_data.setCertificate(object.getString("certification"));
                    film_data.setThumbnailURL(object.getString("poster_path"));

                    FilmDataList.add(film_data);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            SetupRecyclerView(FilmDataList);

            masterFilmAdapter.notifyDataSetChanged();
        }, error -> {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            hidePDialog();
        });

        requestQueue = Volley.newRequestQueue(fContext);
        requestQueue.add(filmJSONRequest);

        return rootView;


    }


    private void SetupRecyclerView(List<MasterFilm_Data> FilmDataList) {

        MasterFilmAdapter masterFilmAdapter = new MasterFilmAdapter(fContext, FilmDataList);

        recyclerView.setAdapter(masterFilmAdapter);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(fContext));
        recyclerView.setLayoutManager(layoutManager);

        //add ItemDecoration
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view.findViewById(R.id.fragment_frame);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
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
