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
import com.thomasp.a30211522.magiplexfilmrentals.adapters.PurchasesAdapter;
import com.thomasp.a30211522.magiplexfilmrentals.model.MagiPlexFilm_DB;
import com.thomasp.a30211522.magiplexfilmrentals.model.MyPurchases_Data;

import java.util.List;


public class MyPurchasesFragment extends Fragment {


    private List<MyPurchases_Data> MyPurchasesDataList;
    private PurchasesAdapter purchasesAdapter;
    public static MagiPlexFilm_DB magiPlexFilm_db;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;


    private Context fContext;
    private View rootView;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "MPparam1";
    private static final String ARG_PARAM2 = "MPparam2";

    private String MPParam1;
    private String MPParam2;

    private OnFragmentInteractionListener mListener;

    public MyPurchasesFragment() {
        // Required empty public constructor for fragment to function on Android
    }


    public static MyPurchasesFragment newInstance(String MPparam1, String MPparam2) {
        MyPurchasesFragment fragment = new MyPurchasesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, MPparam1);
        args.putString(ARG_PARAM2, MPparam2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            MPParam1 = getArguments().getString(ARG_PARAM1);
            MPParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    //Set the inflater for the rootView for this fragment's root layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_purchases, container, false);

        //Set the recyclerView for the rootView and apply spacing
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.purchased_film_feed);


        //Return the rootView and the getter for MyFavourites_Data
        getPurchaseData();
        return rootView;
    }

    private void getPurchaseData() {
        List<MyPurchases_Data> myPurchases_data = LatestFilmsFragment.magiPlexFilm_db.myPurchasesDao().getMyPurchasesData();

        //Use this recyclerView adapter to render the data to these items and set the items into a grid
        PurchasesAdapter purchasesAdapter = new PurchasesAdapter(myPurchases_data, fContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(fContext));
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);


        recyclerView.setAdapter(purchasesAdapter);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view.findViewById(R.id.fragment_frame);
    }



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
        void onFragmentInteraction(Uri uri);
    }

}
