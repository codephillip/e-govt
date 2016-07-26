package com.codephillip.intmain.e_govt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codephillip.intmain.e_govt.sync.SyncAdapter;
import com.codephillip.intmain.e_govt.weather.WeatherFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.CreativeCommonsAttributionNoDerivs30Unported;
import de.psdev.licensesdialog.licenses.GnuGeneralPublicLicense30;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final String TAG = MainActivity.class.getSimpleName();
    int currentFragmentId = R.id.nav_ministries;
    private Tracker mTracker;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        String name = "CODEPHILLIP_ACTIVITY";
        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName("ACTIVITY# " + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());


        //ACTUAL ADVERT
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        //DUMMY ADVERT
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("pref_sync",false)) {
            Log.d(TAG, "onCreate: STARTING SYNCADAPTER");
            SyncAdapter.syncImmediately(this);
            Log.d("SHARED_SWITCH_MAIN", "onCreate: ON");
        }
        else {
            Log.d("SHARED_SWITCH_MAIN", "onCreate: OFF");
        }

        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
                if (isFirstStart) {

                    mTracker.send(new HitBuilders.EventBuilder()
                            .setCategory("FIRST LAUNCH")
                            .setAction("APP INTRO")
                            .build());
                    Intent i = new Intent(MainActivity.this, DefaultIntro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new MinistriesFragment();
        getSupportActionBar().setTitle("Ministries");
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        String name;

        if (id == R.id.nav_ministries) {
            fragment = new MinistriesFragment();
            currentFragmentId = R.id.nav_ministries;
            name = "Ministries";
        }
        else if (id == R.id.nav_districts) {
            fragment = new DistrictsFragment();
            currentFragmentId = R.id.nav_districts;
            name = "District";
        }
        else if (id == R.id.nav_events) {
            fragment = new EventsFragment();
            currentFragmentId = R.id.nav_events;
            name = "Events";
        }
        else if (id == R.id.nav_weather) {
            fragment = new WeatherFragment();
            currentFragmentId = R.id.nav_weather;
            name = "Weather";
        }
        else if (id == R.id.nav_feedback) {
            fragment = new FeedBackActivityFragment();
            currentFragmentId = R.id.nav_feedback;
            name = "Feedback";
        }
        else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
//
        else if (id == R.id.nav_about) {
            final Notices notices = new Notices();
            notices.addNotice(new Notice(getString(R.string.app_name), "http://www.codephillip.com", "Copyright 2016 Codephillip codephillip@gmail.com", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("okhttp",  "http://github.com/square/okhttp","Copyright 2016 Square, Inc.", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("picasso", "http://github.com/square/picasso","Copyright 2013 Square, Inc.", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("android-contentprovider-generator", "https://github.com/BoD/android-contentprovider-generator","Copyright 2013 contentprovider-generator.", new GnuGeneralPublicLicense30()));
            notices.addNotice(new Notice("Paolo Rotolo", "https://github.com/PaoloRotolo/AppIntro","Copyright 2015 Paolo Rotolo.", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("TextDrawable", "https://github.com/amulyakhare/TextDrawable/","Copyright (c) 2014 Amulya Khare.", new MITLicense()));
            notices.addNotice(new Notice("OpenWeatherMap", "http://openweathermap.org/","Copyright (c) 2012-2016 OpenWeatherMap, Inc.", new CreativeCommonsAttributionNoDerivs30Unported()));
            notices.addNotice(new Notice("Udacity", "https://www.udacity.com/course/viewer#!/c-ud853/l-1395568821/m-1643858568","Copyright (C) 2014 The Android Open Source Project.", new ApacheSoftwareLicense20()));

            new LicensesDialog.Builder(this)
                    .setNotices(notices)
                    .setIncludeOwnLicense(true)
                    .build()
                    .show();
            return true;
        }
        else {
            return true;
        }

        getSupportActionBar().setTitle(name);
        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName("FragmentName#" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
