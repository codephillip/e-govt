package com.codephillip.intmain.e_govt.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.mymodel.weathertoday.City;
import com.codephillip.intmain.e_govt.mymodel.weathertoday.ListWeatherToday;
import com.codephillip.intmain.e_govt.mymodel.weathertoday.WeatherToday;
import com.codephillip.intmain.e_govt.provider.weather.WeatherColumns;
import com.codephillip.intmain.e_govt.provider.weather.WeatherContentValues;
import com.codephillip.intmain.e_govt.retrofit.ApiClient;
import com.codephillip.intmain.e_govt.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class WeatherIntentService extends IntentService {

    private static final long HALF_DAY_IN_MILLIS = 1000 * 60 * 60 * 12;
    private static final String TAG = WeatherIntentService.class.getSimpleName();
    //    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    String district = "Jinja";
    private ApiInterface apiInterfaceWeather;
    String cityId;

    public WeatherIntentService() {
        super("WeatherIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "ONHANDLE_INTENT");

        cityId = intent.getStringExtra("cityId");
        district = intent.getStringExtra("districtWeatherIntent");
        Log.d(TAG, "city Id# "+cityId);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String lastNotificationKey = district;
        long lastSync = 0;

        try {
            CursorLoader cursorLoader = new CursorLoader(this, WeatherColumns.CONTENT_URI, new String[] {WeatherColumns.NAME}, WeatherColumns.NAME + " LIKE ?",
                    new String[] {district.concat("%")}, null);
            Cursor cursor = cursorLoader.loadInBackground();
            if (cursor.moveToFirst()){
                lastSync = prefs.getLong(lastNotificationKey, 0);
            } else {
                throw new Exception("No Data");
            }
        } catch (Exception e){
            e.printStackTrace();
            Log.d("WEATHERINTENTSERVICE", e.toString());
        }


        if (System.currentTimeMillis() - lastSync >= HALF_DAY_IN_MILLIS) {

            long deleted = getContentResolver().delete(WeatherColumns.CONTENT_URI, null, null);
            Log.d("CONTENT_QUERY_deleted#", String.valueOf(deleted));

            try {
                loadWeatherToday();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong(lastNotificationKey, System.currentTimeMillis());
                editor.apply();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("URL BUG", e.toString());
            }
        }
    }

    private void loadWeatherToday() {
        apiInterfaceWeather = ApiClient.getClient(Utility.WEATHER_BASE_URL).create(ApiInterface.class);
        Log.d(TAG, "loadWeatherToday: cityId " + cityId);

        Call<WeatherToday> call = apiInterfaceWeather.allWeatherToday(Utility.WEATHER_BASE_URL + "/data/2.5/forecast?id=" + cityId + "&mode=json&units=metric&cnt=7&appid=1f846e7a0e00cf8c2f96dd5e768580fb");
        call.enqueue(new Callback<WeatherToday>() {
            @Override
            public void onResponse(Call<WeatherToday> call, retrofit2.Response<WeatherToday> response) {
                WeatherToday wd = response.body();
                storeInTodayWeatherTable(wd);
            }

            @Override
            public void onFailure(Call<WeatherToday> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                throw new UnsupportedOperationException("Failed to start");
            }
        });
    }

    private void storeInTodayWeatherTable(WeatherToday wd) {
        if (wd == null)
            throw new NullPointerException("Weathertoday not found");

        List<ListWeatherToday> listWeatherToday = wd.getListWeatherToday();
        City city = wd.getCity();

        for (ListWeatherToday weather : listWeatherToday) {
            Log.d(TAG, "saveWeathertoday: " + weather.getDtTxt() + weather.getMain().getTempMin() + weather.getMain().getTempMax() + weather.getWeather().get(0).getMain() + weather.getWeather().get(0).getDescription());
            Log.d("INSERT: ", "starting");
            WeatherContentValues values = new WeatherContentValues();
            values.putDate(weather.getDt());
            values.putDateTxt(weather.getDtTxt());
            values.putName(city.getName());
            values.putMain(weather.getWeather().get(0).getMain());
            values.putWeatherId(weather.getWeather().get(0).getId());
            values.putMaxTemp(weather.getMain().getTempMax());
            values.putMinTemp(weather.getMain().getTempMin());
            values.putWindSpeed(weather.getWind().getSpeed());
            values.putDeg(weather.getWind().getDeg());
            values.putHumidity(weather.getMain().getHumidity());
            values.putPressure(weather.getMain().getPressure());
            Uri uri = values.insert(getContentResolver());
            Log.d("INSERT: ", uri.toString());
        }
    }
}
