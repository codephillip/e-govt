package com.codephillip.intmain.e_govt;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by codephillip on 1/4/16.
 */
public class DefaultIntro extends AppIntro {

    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.
//        addSlide(first_fragment);
//        addSlide(second_fragment);
//        addSlide(third_fragment);
//        addSlide(fourth_fragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance("Welcome", "To E-gov't, your guide to Government projects", R.mipmap.ic_launcher, Color.parseColor("#00B0FF")));
        addSlide(AppIntroFragment.newInstance("Ministries", "Get on going Ministry projects", R.drawable.ic_business_black_24dp, Color.parseColor("#FFC400")));
        addSlide(AppIntroFragment.newInstance("Events", "Found out about Government events", R.drawable.ic_event_black_24dp, Color.parseColor("#FF1744")));
        addSlide(AppIntroFragment.newInstance("Weather", "And be the first to know the weather", R.drawable.art_rain, Color.parseColor("#FF1744")));
//        addSlide(AppIntroFragment.newInstance(title, description, image, background_colour));
//
        // OPTIONAL METHODS

//        // SHOW or HIDE the statusbar
//        showStatusBar(false);
//
//        // Edit the color of the nav bar on Lollipop+ devices
//        setNavBarColor(Color.parseColor("#3F51B5"));
//
//        // Turn vibration on and set intensity
//        // NOTE: you will need to ask VIBRATE permission in Manifest if you haven't already
//        setVibrate(true);
//        setVibrateIntensity(30);
//
//        // Animations -- use only one of the below. Using both could cause errors.
//        setFadeAnimation(); // OR
//        setZoomAnimation(); // OR
//        setFlowAnimation(); // OR
//        setSlideOverAnimation(); // OR
        setDepthAnimation(); // OR
//        setCustomTransformer(yourCustomTransformer);
//
//        // Permissions -- takes a permission and slide number
//        askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
    }

    @Override
    public void onSkipPressed() {
        finish();
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}
