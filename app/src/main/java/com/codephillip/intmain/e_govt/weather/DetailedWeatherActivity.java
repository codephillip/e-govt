package com.codephillip.intmain.e_govt.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.codephillip.intmain.e_govt.AnalyticsApplication;
import com.codephillip.intmain.e_govt.R;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class DetailedWeatherActivity extends AppCompatActivity {

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        String name = "CODEPHILLIP";
        Log.i("districtWeather", "Setting screen name: " + name);
        mTracker.setScreenName("ACTIVITY# " + "DetailedWeather");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
