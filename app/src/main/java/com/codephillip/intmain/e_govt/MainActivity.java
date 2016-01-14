package com.codephillip.intmain.e_govt;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int currentFragmentId = R.id.nav_ministries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TESTING DATA INSERTION
//        ChaptersContentValues values = new ChaptersContentValues();
//        values.putDate("3-4-2016");
//        values.putDistrict("Kampala");
//        final Uri uri = values.insert(getContentResolver());
//        Log.d("CONTENT#", uri.toString());
//

        //TESTING DATA QUERYING
//        CursorLoader cursorLoader = new CursorLoader(this, ChaptersColumns.CONTENT_URI, null, null, null, null);
//        Cursor cursor = cursorLoader.loadInBackground();
//
//        if (cursor.moveToFirst()){
//            Log.d("CONTENT_QUERY#", cursor.getString(cursor.getColumnIndex(ChaptersColumns.DISTRICT)));
//        }

//        startService(new Intent(MainActivity.this, MyIntentService.class));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new MinistriesFragment();
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
        }
        else if (id == R.id.nav_districts) {
            fragment = new DistrictsFragment();
            currentFragmentId = R.id.nav_districts;
        }
// else if (id == R.id.nav_events) {
//
//        } else if (id == R.id.nav_weather) {
//
//        } else if (id == R.id.nav_settings) {
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
