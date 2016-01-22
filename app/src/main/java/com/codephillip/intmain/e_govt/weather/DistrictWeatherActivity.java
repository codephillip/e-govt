package com.codephillip.intmain.e_govt.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.codephillip.intmain.e_govt.R;

public class DistrictWeatherActivity extends AppCompatActivity {


    String intentString = "Jinja";
    String districtString = "districtString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            intentString = getIntent().getStringExtra("districtWeatherIntent");
            Log.d("INTENT", intentString);
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putString(districtString, intentString);
//            editor.apply();
        } catch (Exception e){
            e.printStackTrace();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            intentString = prefs.getString(districtString,"Kampala");
            Log.d("PREF#", intentString);
        }

        getSupportActionBar().setTitle(intentString);

    }
}
