package com.thomasp.a30211522.magiplexfilmrentals.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class LatestFilmsFragment extends Fragment {


    private static final String JSON_URL = "https://api.themoviedb.org/3/movie/latest?api_key=f86cdcb19008ff7337dfe14255d3139f&language=en-GB";
    private static final String TAG = LatestFilmsFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    private List<MasterFilm_Data> FilmDataList = new ArrayList<>();
    private MasterFilmAdapter masterFilmAdapter;
    public static MagiPlexFilm_DB magiPlexFilm_db;

    private static final int VERTICAL_ITEM_SPACE = 48;

    private Context fContext;
    private View rootView;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "LF_param1";
    private static final String ARG_PARAM2 = "LF_param2";

    private String LFparam1;
    private String LFparam2;

    private OnFragmentInteractionListener mListener;

    public LatestFilmsFragment() {
        // Required empty public constructor for fragment to function on Android
    }

    public static LatestFilmsFragment newInstance(String LF_param1, String LF_param2) {
        LatestFilmsFragment fragment = new LatestFilmsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, LF_param1);
        args.putString(ARG_PARAM2, LF_param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            LFparam1 = getArguments().getString(ARG_PARAM1);
            LFparam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    //Set the inflater for the rootView for this fragment's root layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_latest_films, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.latest_film_feed);

        masterFilmAdapter = new MasterFilmAdapter(getActivity(), FilmDataList);

        recyclerView.setAdapter(masterFilmAdapter);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //add ItemDecoration
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        progressDialog = new ProgressDialog(getActivity());
        //Show progress dialog before making http request
        progressDialog.setMessage("Retrieving...");
        progressDialog.show();


        JsonArrayRequest filmJSONRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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

                masterFilmAdapter.notifyDataSetChanged();
            }

        }, error -> {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            hidePDialog();
        });

        requestQueue = Volley.newRequestQueue(fContext);

        return rootView;

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
