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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static int SYNC_INTERVAL = 60 * 360;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;
    final String TAG = MainActivity.class.getSimpleName();

    int currentFragmentId = R.id.nav_ministries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

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

        if (id == R.id.nav_ministries) {
            fragment = new MinistriesFragment();
            currentFragmentId = R.id.nav_ministries;
            getSupportActionBar().setTitle("Ministries");
        }
        else if (id == R.id.nav_districts) {
            fragment = new DistrictsFragment();
            currentFragmentId = R.id.nav_districts;
            getSupportActionBar().setTitle("Districts");
        }
        else if (id == R.id.nav_events) {
            fragment = new EventsFragment();
            currentFragmentId = R.id.nav_events;
            getSupportActionBar().setTitle("Events");
        }
        else if (id == R.id.nav_weather) {
            fragment = new WeatherFragment();
            currentFragmentId = R.id.nav_weather;
            getSupportActionBar().setTitle("Weather");
        }
        else if (id == R.id.nav_feedback) {
            fragment = new FeedBackActivityFragment();
            currentFragmentId = R.id.nav_feedback;
            getSupportActionBar().setTitle("Feedback");
        }
//
// else if (id == R.id.nav_settings) {
//
//        } else if (id == R.id.nav_about) {
//
//        }
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
}
