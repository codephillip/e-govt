package com.codephillip.intmain.e_govt.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.adapter.ForecastAdapter;
import com.codephillip.intmain.e_govt.provider.weather.WeatherColumns;

public class DistrictWeatherActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    String intentString = "Jinja";
    String districtString = "districtString";
    private ListView mListView;
    private ForecastAdapter mForecastAdapter;
    private int mPosition = ListView.INVALID_POSITION;
    private boolean mUseTodayLayout;
    private static final String SELECTED_KEY = "selected_position";
    private static final int FORECAST_LOADER = 0;

    public DistrictWeatherActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_district_weather, container, false);

//        try {
//            intentString = getActivity().getIntent().getStringExtra("districtWeatherIntent");
//            Log.d("INTENT", intentString);
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putString(districtString, intentString);
//            editor.apply();
//        } catch (Exception e){
//            e.printStackTrace();
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//            intentString = prefs.getString(districtString,"Kampala");
//            Log.d("PREF#", intentString);
//        }
//
//        getContext().getSupportActionBar().setTitle("Ministries");


        mForecastAdapter = new ForecastAdapter(getActivity(), null, 0);

//        Button button = (Button) rootView.findViewById(R.id.button1);
//        button.setText(intentString);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), DetailedWeatherActivity.class).putExtra("dateIntent", intentString));
//            }
//        });

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_forecast);
        mListView.setAdapter(mForecastAdapter);
        // We'll call our MainActivity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // CursorAdapter returns a cursor at the correct position for getItem(), or null
                // if it cannot seek to that position.
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                if (cursor != null) {
//                    String locationSetting = Utility.getPreferredLocation(getActivity());
//                    ((Callback) getActivity())
//                            .onItemSelected(WeatherContract.WeatherEntry.buildWeatherLocationWithDate(
//                                    locationSetting, cursor.getLong(COL_WEATHER_DATE)
//                            ));
                    startActivity(new Intent(getContext(), DetailedWeatherActivity.class).putExtra("currentDate", "monday"));
//                    startActivity(new Intent(getContext(), DetailedWeatherActivity.class).putExtra("date", view.getId()));

                }
                mPosition = position;
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        mForecastAdapter.setUseTodayLayout(mUseTodayLayout);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // When tablets rotate, the currently selected list item needs to be saved.
        // When no item is selected, mPosition will be set to Listview.INVALID_POSITION,
        // so check for that before storing.
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
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

        getLoaderManager().initLoader(FORECAST_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), WeatherColumns.CONTENT_URI, null, WeatherColumns.NAME + " LIKE ?",
                new String[] {intentString.concat("%")}, null);
//        return new CursorLoader(getContext(), WeatherColumns.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mForecastAdapter.swapCursor(data);
        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore
            // to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mForecastAdapter.swapCursor(null);
    }

    public void setUseTodayLayout(boolean useTodayLayout) {
        mUseTodayLayout = useTodayLayout;
        if (mForecastAdapter != null) {
            mForecastAdapter.setUseTodayLayout(mUseTodayLayout);
        }
    }
}
