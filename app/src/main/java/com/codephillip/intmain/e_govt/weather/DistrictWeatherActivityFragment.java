package com.codephillip.intmain.e_govt.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codephillip.intmain.e_govt.R;

public class DistrictWeatherActivityFragment extends Fragment {

    String intentString = "Jinja";
    String districtString = "districtString";

    public DistrictWeatherActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_district_weather, container, false);

        try {
            intentString = getActivity().getIntent().getStringExtra("districtWeatherIntent");
            Log.d("INTENT", intentString);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(districtString, intentString);
            editor.apply();
        } catch (Exception e){
            e.printStackTrace();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            intentString = prefs.getString(districtString,"Kampala");
            Log.d("PREF#", intentString);
        }

        Button button = (Button) rootView.findViewById(R.id.button1);
        button.setText(intentString);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DetailedWeatherActivity.class).putExtra("dateIntent", intentString));
            }
        });
        
        return rootView;
    }
}
