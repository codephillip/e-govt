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

/**
 * Copyright 2016 Kigenyi Phillip
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static int SYNC_INTERVAL = 60 * 360;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;
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
        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.adView);

//        mAdView.setAdSize(AdSize.BANNER);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);


        //////SEND EVENT
//        mTracker.send(new HitBuilders.EventBuilder()
//                .setCategory("Action")
//                .setAction("Share")
//                .build());


//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//        r.play();
//
//        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        // Vibrate for 500 milliseconds
//        v.vibrate(500);

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        if (prefs.getBoolean("notifications_new_message",true)){
//            Uri notification = Uri.parse(prefs.getString("notifications_new_message_ringtone", "content://settings/system/notification_sound"));
//            Ringtone r = RingtoneManager.getRingtone(this, notification);
//            r.play();
//        }
//
//        if (prefs.getBoolean("notifications_new_message_vibrate", true)){
//            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//            // Vibrate for 500 milliseconds
//            v.vibrate(500);
//        }
//
//        String syncFrequency = preferences.getString("sync_frequency", "360");
//        Log.d("SYNCADAPTER", "SyncFrequency "+syncFrequency);
//        SYNC_INTERVAL = 60 * Integer.parseInt(syncFrequency);
//        SyncAdapter.configurePeriodicSync(this, SYNC_INTERVAL, SYNC_FLEXTIME);

        Log.d(TAG, "onCreate: STARTING SYNCADAPTER");
        SyncAdapter.initializeSyncAdapter(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("pref_sync",false)) {
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

                //  If the activity has never started before...
//                if (true) {
                if (isFirstStart) {

                    mTracker.send(new HitBuilders.EventBuilder()
                            .setCategory("FIRST LAUNCH")
                            .setAction("APP INTRO")
                            .build());

                    //  Launch app intro
                    Intent i = new Intent(MainActivity.this, DefaultIntro.class);
                    startActivity(i);

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();

//        boolean loginBoolean;
////        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        loginBoolean = preferences.getBoolean("login", false);
//
//        if (loginBoolean){
////            Toast.makeText(MainActivity.this, "You are successfully logged in", Toast.LENGTH_LONG).show();
//            Log.d(TAG, "onCreate: logged in successfully");
//        }
//        else {
//            startActivity(new Intent(this, LoginActivity.class));
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        mTracker = application.getDefaultTracker();

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        Fragment fragment = null;
        String name;

        if (id == R.id.nav_ministries) {
            fragment = new MinistriesFragment();
            currentFragmentId = R.id.nav_ministries;
            name = "Ministries";
            getSupportActionBar().setTitle(name);
            Log.i(TAG, "Setting screen name: " + name);
            mTracker.setScreenName("FragmentName#" + name);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
        else if (id == R.id.nav_districts) {
            fragment = new DistrictsFragment();
            currentFragmentId = R.id.nav_districts;
            name = "District";
            getSupportActionBar().setTitle(name);
            Log.i(TAG, "Setting screen name: " + name);
            mTracker.setScreenName("FragmentName#" + name);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
        else if (id == R.id.nav_events) {
            fragment = new EventsFragment();
            currentFragmentId = R.id.nav_events;
            name = "Events";
            getSupportActionBar().setTitle(name);
            Log.i(TAG, "Setting screen name: " + name);
            mTracker.setScreenName("FragmentName#" + name);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
        else if (id == R.id.nav_weather) {
            fragment = new WeatherFragment();
            currentFragmentId = R.id.nav_weather;
            name = "Weather";
            getSupportActionBar().setTitle(name);
            Log.i(TAG, "Setting screen name: " + name);
            mTracker.setScreenName("FragmentName#" + name);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
        else if (id == R.id.nav_feedback) {
            fragment = new FeedBackActivityFragment();
            currentFragmentId = R.id.nav_feedback;
            name = "Feedback";
            getSupportActionBar().setTitle(name);
            Log.i(TAG, "Setting screen name: " + name);
            mTracker.setScreenName("FragmentName#" + name);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
        else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
//
        else if (id == R.id.nav_about) {
            final Notices notices = new Notices();
            notices.addNotice(new Notice("E-govt", "http://www.codephillip.com", "Copyright 2016 Kigenyi Phillip codephillip@gmail.com", new ApacheSoftwareLicense20()));
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

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void resumeFragment(){
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        String lastKey = this.getString(R.string.pref_last_key);
//        long lastFragment2 = prefs.getLong(lastKey, 0);
//        int id2 = (int) lastFragment2;
//        if (id2 != 200){
//            String lastFragmentKey = this.getString(R.string.pref_last_fragment);
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putLong(lastFragmentKey, R.id.nav_race);
//            editor.commit();
//        }
//
//        String lastFragmentKey = this.getString(R.string.pref_last_fragment);
//        long lastFragment = prefs.getLong(lastFragmentKey, R.id.nav_race);
//
//        int id = (int) lastFragment;
//        Fragment fragment = null;
//
//        if (id == R.id.nav_race) {
//            fragment = new RaceFragment();
//            currentFragmentId = R.id.nav_race;
//        } else if (id == R.id.nav_records) {
//            fragment = new RecordFragment();
//            currentFragmentId = R.id.nav_records;
//        } else if (id == R.id.nav_stretches) {
//            fragment = new StretchFragment();
//            currentFragmentId = R.id.nav_stretches;
//        }
//        else {
//        }
////        } else if (id == R.id.nav_settings) {
////
////        } else if (id == R.id.nav_feedback) {
////
////        }
//
//
//        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.frame,fragment);
//        fragmentTransaction.commit();
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//    }


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
