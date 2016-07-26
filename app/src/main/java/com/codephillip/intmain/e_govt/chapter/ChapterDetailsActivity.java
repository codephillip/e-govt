package com.codephillip.intmain.e_govt.chapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codephillip.intmain.e_govt.AnalyticsApplication;
import com.codephillip.intmain.e_govt.FeedBackActivity;
import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class ChapterDetailsActivity extends AppCompatActivity {
    public CollapsingToolbarLayout ctb;
    private int default_code = 0x000000;
    String ministry;
    String tableName;
    String district = "Kampala";
    String location = "Serena Hotel";
    String intentString;
    public boolean eventBoolean = false;
    private Tracker mTracker;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        mTracker.setScreenName("ACTIVITY# " + "ChapterDetailsActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());


        //ACTUAL ADVERT
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        //DUMMY ADVERT
        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.adView);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        //TODO RESTORE DUMY ADD FOR BETA
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);

        ctb = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        try {
            intentString = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            Log.d("INTENT", intentString);
        }catch (Exception e){
            e.printStackTrace();
            try {
                intentString = getIntent().getStringExtra("eventIntent");
                Log.d("INTENT", intentString);
                eventBoolean = true;
                Log.d("BOOLEAN", String.valueOf(eventBoolean));
            }catch (Exception e1){
                e1.printStackTrace();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                String lastKey = "Feedback";
                String tableKey = "Feedback_tablename";
                intentString = prefs.getString(lastKey, intentString);
                Log.d("PREF#", intentString);

                String tableName = prefs.getString(tableKey, "events");

                eventBoolean = tableName.equals("events");
            }

        }

        ImageView toolbarImage = (ImageView) findViewById(R.id.image_chapter_details);
        TextView chapterText = (TextView) findViewById(R.id.chapter_text);
        TextView bodyText = (TextView) findViewById(R.id.body_text);
        TextView dateText = (TextView) findViewById(R.id.dateText);
        TextView locationText = (TextView) findViewById(R.id.locationText);

        CursorLoader cursorLoader;
        if (eventBoolean) cursorLoader = new CursorLoader(this, EventsColumns.CONTENT_URI, null, null, null, null);
        else cursorLoader = new CursorLoader(this, ChaptersColumns.CONTENT_URI, null, null, null, null);

//        CursorLoader cursorLoader = new CursorLoader(this, ChaptersColumns.CONTENT_URI, null, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        String chapterTitleString = null;

        Log.d("CHAPTERDETAILS#", "CURSORMOVING");

        if (eventBoolean){
            Log.d("CHAPTERDETAILS#", "EVENT_QUERY");

            ctb.setTitle("Event Details");
            if (cursor.moveToFirst()){
                Log.d("CHAPTERDETAILS#", "CURSORMOVING###event");
                do {
                    chapterTitleString = cursor.getString(cursor.getColumnIndex(EventsColumns.TITLE));
                    if (chapterTitleString.equals(intentString)){
                        String bodyTextString = cursor.getString(cursor.getColumnIndex(EventsColumns.STORY));
                        String imageUrl = cursor.getString(cursor.getColumnIndex(EventsColumns.IMAGE));
                        String dateTextString = cursor.getString(cursor.getColumnIndex(EventsColumns.DATE));
                        ministry = cursor.getString(cursor.getColumnIndex(EventsColumns.MINISTRY));
                        location = cursor.getString(cursor.getColumnIndex(EventsColumns.LOCATION));
                        tableName = "events";
                        Log.d("CONTENT", "RESULTS: " + chapterTitleString + "#" + bodyTextString + "#" + chapterTitleString + "#" + imageUrl + "#" + location + "#" + ministry);
                        chapterText.setText(chapterTitleString);
                        bodyText.setText(bodyTextString);
                        dateText.setText(dateTextString);
                        locationText.setText(location);
                        Utility.picassoLoader(this, toolbarImage, imageUrl);
                        break;
                    }
                }while (cursor.moveToNext());
            }
        }

        else {

            if (cursor.moveToFirst()){
                Log.d("CHAPTERDETAILS#", "CURSORMOVING###");
                do {
                    chapterTitleString = cursor.getString(cursor.getColumnIndex(ChaptersColumns.TITLE));
                    if (chapterTitleString.equals(intentString)){
                        String bodyTextString = cursor.getString(cursor.getColumnIndex(ChaptersColumns.STORY));
                        String imageUrl = cursor.getString(cursor.getColumnIndex(ChaptersColumns.IMAGE));
                        String dateTextString = cursor.getString(cursor.getColumnIndex(ChaptersColumns.DATE));
                        ministry = cursor.getString(cursor.getColumnIndex(ChaptersColumns.MINISTRY));
                        district = cursor.getString(cursor.getColumnIndex(ChaptersColumns.DISTRICT));
                        tableName = "chapters";
                        Log.d("CONTENT", "RESULTS: " + chapterTitleString + "#" + bodyTextString + "#" + chapterTitleString + "#" + imageUrl + "#" + district + "#" + ministry);
                        chapterText.setText(chapterTitleString);
                        bodyText.setText(bodyTextString);
                        dateText.setText(dateTextString);
                        locationText.setVisibility(View.GONE);
                        Utility.picassoLoader(this, toolbarImage, imageUrl);
                        break;
                    }
                }while (cursor.moveToNext());
            }
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final String finalChapterTitle = chapterTitleString;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), FeedBackActivity.class).putExtra(Intent.EXTRA_TEXT, finalChapterTitle).putExtra("tableName", tableName));

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bitmap bitmap = ((BitmapDrawable) toolbarImage.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int vibrant = palette.getVibrantColor(default_code);
                int vibrantLight = palette.getLightVibrantColor(default_code);
                int vibrantDark = palette.getDarkVibrantColor(default_code);
                int muted = palette.getMutedColor(default_code);
                int mutedLight = palette.getLightMutedColor(default_code);
                int mutedDark = palette.getDarkMutedColor(default_code);

                final int[] colorArray = {
                        vibrant, vibrantLight, vibrantDark, muted, mutedLight, mutedDark
                };
                /* Set toolbar color from the palette */
                try {
                    ctb.setContentScrimColor(colorArray[Utility.randInt(0, 5)]);
                } catch (Exception e) {
                    e.printStackTrace();
                    ctb.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });
    }

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String key = "Frag";
        String fragPref = prefs.getString(key, "Ministry");
        Log.d("PREF#", intentString);

        SharedPreferences.Editor editor = prefs.edit();
        String FragString = "FragNo";
        String chapterStrip = "Details";

        if (fragPref.equals("Ministry")){
            editor.putInt(FragString, 1);
            editor.putString(chapterStrip, ministry);
            Log.d("DETAILS#", ministry);
        }
        else if (fragPref.equals("District")){
            editor.putInt(FragString, 2);
            editor.putString(chapterStrip, district);
            Log.d("DETAILS#", district);
        }
        editor.apply();
        eventBoolean = false;
    }

//    @Override
//    protected void onPause() {
//        if (mAdView != null) {
//            mAdView.pause();
//        }
//        super.onPause();
//    }

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
