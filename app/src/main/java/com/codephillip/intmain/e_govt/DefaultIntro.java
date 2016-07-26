package com.codephillip.intmain.e_govt;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by codephillip on 1/4/16.
 */
public abstract class DefaultIntro extends AppIntro {

    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

        addSlide(AppIntroFragment.newInstance("Welcome", "To E-gov't, your guide to Government projects", R.drawable.uganda_map, Color.parseColor("#00B0FF")));
        addSlide(AppIntroFragment.newInstance("Ministries", "Follow-up on Ministry projects", R.drawable.ic_business_white_48dp, Color.parseColor("#424242")));
        addSlide(AppIntroFragment.newInstance("Events", "Found out about Government events", R.drawable.ic_event_white_48dp, Color.parseColor("#F9A825")));
        addSlide(AppIntroFragment.newInstance("Feedback", "Give feedback about Government's performance", R.drawable.ic_feedback_white_48dp, Color.parseColor("#C62828")));
        addSlide(AppIntroFragment.newInstance("Weather", "And be the first to know the weather", R.drawable.art_rain, Color.parseColor("#424242")));

        setDepthAnimation(); // OR
    }

    @Override
    public void onSkipPressed() {
        finish();
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }
}
