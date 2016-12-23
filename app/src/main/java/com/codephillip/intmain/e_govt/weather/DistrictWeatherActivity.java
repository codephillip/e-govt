package com.codephillip.intmain.e_govt.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.codephillip.intmain.e_govt.AnalyticsApplication;
import com.codephillip.intmain.e_govt.R;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class DistrictWeatherActivity extends AppCompatActivity {

    private Tracker mTracker;
    String intentString = "Jinja";
    String districtString = "districtString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        String name = "CODEPHILLIP";
        Log.i("districtWeather", "Setting screen name: " + name);
        mTracker.setScreenName("ACTIVITY# " + "DistrictWeather");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            intentString = getIntent().getStringExtra("districtWeatherIntent");
            Log.d("INTENT", intentString);
        } catch (Exception e){
            e.printStackTrace();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            intentString = prefs.getString(districtString,"Kampala");
            Log.d("PREF#", intentString);
        }

        getSupportActionBar().setTitle(intentString);

    }
}
