package com.codephillip.intmain.e_govt.chapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codephillip.intmain.e_govt.AnalyticsApplication;
import com.codephillip.intmain.e_govt.R;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class ChapterActivity extends AppCompatActivity {

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        mTracker.setScreenName("ACTIVITY# " + "ChapterActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
