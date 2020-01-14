package com.thomasp.a30211522.magiplexfilmrentals.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import com.google.android.material.navigation.NavigationView;
import com.thomasp.a30211522.magiplexfilmrentals.R;
import com.thomasp.a30211522.magiplexfilmrentals.model.MagiPlexFilm_DB;


public class MasterActivity extends AppCompatActivity {

    private DrawerLayout Dlayout;
    private Toolbar Mastertoolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private ConnectivityReceiver Creceiver = new ConnectivityReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);


        //Set a Toolbar to replace the ActionBar
        Mastertoolbar = (Toolbar) findViewById(R.id.master_toolbar);
        setSupportActionBar(Mastertoolbar);
        //This will display an Up icon (<-), we will replace it with hamburger later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Find our drawer view
        Dlayout = (DrawerLayout) findViewById(R.id.master_layout);
        drawerToggle = setupDrawerToggle();

        //Setup toggle to display hamburger icon with animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();


        //Tie Dlayout events to the ActionBarToggle
        Dlayout.addDrawerListener(drawerToggle);


        // Find our drawer view
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // Setup drawer view
        setupDrawerContent(navigationView);

        if (savedInstanceState == null) {
            // on first time to display view for first navigation item based on the number
            onSectionAttached(1); // 1 is your fragment's number for "Latest Films Fragment"
        }


        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        Creceiver = new ConnectivityReceiver();
        this.registerReceiver(Creceiver, intentFilter);


        MagiPlexFilm_DB magiPlexFilmCreateDB = (MagiPlexFilm_DB) Room.databaseBuilder(this, MagiPlexFilm_DB.class, "masterdb").build();


    }


    public void onSectionAttached(int number) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        switch (number) {
            case 1:
                fragment = new LatestFilmsFragment();
                break;
            case 2:
                fragment = new AllFilmsFragment();
                break;
            case 3:
                fragment = new MyFavouritesFragment();
                break;
            case 4:
                fragment = new MyPurchasesFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_frame, fragment).commit();

        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, Dlayout, Mastertoolbar, R.string.drawer_open, R.string.drawer_close);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    public void selectDrawerItem(MenuItem menuItem) {
        //Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_latest_films:
                fragmentClass = LatestFilmsFragment.class;
                break;
            case R.id.nav_all_films:
                fragmentClass = AllFilmsFragment.class;
                break;
            case R.id.nav_my_favourites:
                fragmentClass = MyFavouritesFragment.class;
                break;
            case R.id.nav_my_purchases:
                fragmentClass = MyPurchasesFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = SettingsActivity.class;
                break;
            default:
                fragmentClass = LatestFilmsFragment.class;
        }


        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        assert fragment != null;
        fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragment).commit();


        //Highlight the selected item has been done by the Navigation View
        menuItem.setChecked(true);
        //Set action bar title
        setTitle(menuItem.getTitle());
        //Close the navigation drawer
        Dlayout.closeDrawers();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            Dlayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Creceiver != null) {
            this.unregisterReceiver(Creceiver);
        }
    }


    public class ConnectivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager conn =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context, "Wi-Fi is now connected", Toast.LENGTH_SHORT).show();
            } else if (networkInfo != null) {
                Toast.makeText(context, "Wi-Fi is now disconnected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "No active network connection is available", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
