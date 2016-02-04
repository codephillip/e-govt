package com.codephillip.intmain.e_govt.weather;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.provider.weather.WeatherColumns;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailedWeatherActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private ImageView mIconView;
    private TextView mFriendlyDateView;
    private TextView mDateView;
    private TextView mDescriptionView;
    private TextView mHighTempView;
    private TextView mLowTempView;
    private TextView mHumidityView;
    private TextView mWindView;
    private TextView mPressureView;
    private static final int DETAIL_LOADER = 0;
    private String mForecast;
    int intentInt;

    public DetailedWeatherActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detailed_weather, container, false);
        mIconView = (ImageView) rootView.findViewById(R.id.detail_icon);
        mDateView = (TextView) rootView.findViewById(R.id.detail_date_textview);
        mFriendlyDateView = (TextView) rootView.findViewById(R.id.detail_day_textview);
        mDescriptionView = (TextView) rootView.findViewById(R.id.detail_forecast_textview);
        mHighTempView = (TextView) rootView.findViewById(R.id.detail_high_textview);
        mLowTempView = (TextView) rootView.findViewById(R.id.detail_low_textview);
        mHumidityView = (TextView) rootView.findViewById(R.id.detail_humidity_textview);
        mWindView = (TextView) rootView.findViewById(R.id.detail_wind_textview);
        mPressureView = (TextView) rootView.findViewById(R.id.detail_pressure_textview);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), WeatherColumns.CONTENT_URI, null, WeatherColumns.DATE + " LIKE ?",
                new String[] {String.valueOf(intentInt).concat("%")}, null);
//        return new CursorLoader(getContext(), WeatherColumns.CONTENT_URI, null, null,
//                null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data != null && data.moveToFirst()) {
            // Read weather condition ID from cursor
            int weatherId = data.getInt(data.getColumnIndex(WeatherColumns.WEATHER_ID));

            // Use weather art image
            mIconView.setImageResource(Utility.getArtResourceForWeatherCondition(weatherId));

            // Read date from cursor and update views for day of week and date
            long date = data.getLong(data.getColumnIndex(WeatherColumns.DATE));
            String friendlyDateText = Utility.getDayName(getActivity(), date);
            String dateText = data.getString(data.getColumnIndex(WeatherColumns.DATE_TXT));
//            String dateText = Utility.getFormattedMonthDay(getActivity(), date);
            mFriendlyDateView.setText(friendlyDateText);
            mFriendlyDateView.setVisibility(View.GONE);
            mDateView.setText(dateText);

            // Read description from cursor and update view
            String description = data.getString(data.getColumnIndex(WeatherColumns.MAIN));
            mDescriptionView.setText(description);

            // For accessibility, add a content description to the icon field
            mIconView.setContentDescription(description);

            // Read high temperature from cursor and update view
            boolean isMetric = Utility.isMetric(getActivity());

            double high = data.getDouble(data.getColumnIndex(WeatherColumns.MAX_TEMP));
            String highString = Utility.formatTemperature(getActivity(), high);
            mHighTempView.setText(highString);

            // Read low temperature from cursor and update view
            double low = data.getDouble(data.getColumnIndex(WeatherColumns.MIN_TEMP));
            String lowString = Utility.formatTemperature(getActivity(), low);
            mLowTempView.setText(lowString);

            // Read humidity from cursor and update view
            float humidity = data.getFloat(data.getColumnIndex(WeatherColumns.HUMIDITY));
            mHumidityView.setText(getActivity().getString(R.string.format_humidity, humidity));

            // Read wind speed and direction from cursor and update view
            float windSpeedStr = data.getFloat(data.getColumnIndex(WeatherColumns.WIND_SPEED));
            float windDirStr = data.getFloat(data.getColumnIndex(WeatherColumns.DEG));
            mWindView.setText(Utility.getFormattedWind(getActivity(), windSpeedStr, windDirStr));

            // Read pressure from cursor and update view
            float pressure = data.getFloat(data.getColumnIndex(WeatherColumns.PRESSURE));
            mPressureView.setText(getActivity().getString(R.string.format_pressure, pressure));

            // We still need this for the share intent
            mForecast = String.format("%s - %s - %s/%s", dateText, description, high, low);

//            // If onCreateOptionsMenu has already happened, we need to update the share intent now.
//            if (mShareActionProvider != null) {
//                mShareActionProvider.setShareIntent(createShareForecastIntent());
//            }
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        try {
            intentInt = getActivity().getIntent().getIntExtra("currentDate", 0);
            Log.d("INTENT", "DATE_VALUE " + intentInt);
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putString(districtString, intentInt);
//            editor.apply();
        } catch (Exception e){
            e.printStackTrace();
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//            intentInt = prefs.getString(districtString,"Kampala");
//            Log.d("PREF#", intentInt);
        }
        getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }
}
