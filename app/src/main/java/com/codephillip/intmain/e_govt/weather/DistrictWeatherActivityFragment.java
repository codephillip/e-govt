package com.codephillip.intmain.e_govt.weather;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codephillip.intmain.e_govt.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DistrictWeatherActivityFragment extends Fragment {

    public DistrictWeatherActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_district_weather, container, false);
    }
}
