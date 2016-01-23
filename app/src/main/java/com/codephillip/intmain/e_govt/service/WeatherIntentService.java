package com.codephillip.intmain.e_govt.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.CursorLoader;
import android.text.format.Time;
import android.util.Log;

import com.codephillip.intmain.e_govt.provider.weather.WeatherColumns;
import com.codephillip.intmain.e_govt.provider.weather.WeatherContentValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherIntentService extends IntentService {

    private static final long HALF_DAY_IN_MILLIS = 1000 * 60 * 60 * 12;
    //    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    String district = "Jinja";

    public WeatherIntentService() {
        super("WeatherIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("WEATHER_INTENT_SERVICE", "ONHANDLE_INTENT");

        //
        int cityId = intent.getIntExtra("cityId", 233114);
        district = intent.getStringExtra("districtWeatherIntent");
        Log.d("WEATHER_INTENT_SERVICE", "city Id#"+cityId);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String lastNotificationKey = district;
//        String lastNotificationKey = this.getString(R.string.pref_last_notification);
        long lastSync;

        CursorLoader cursorLoader = new CursorLoader(this, WeatherColumns.CONTENT_URI, new String[] {WeatherColumns.NAME}, WeatherColumns.NAME + " LIKE ?",
                new String[] {district.concat("%")}, null);
//        CursorLoader cursorLoader = new CursorLoader(this, WeatherColumns.CONTENT_URI, new String[] {WeatherColumns.NAME},null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        if (cursor.moveToFirst()){
            lastSync = prefs.getLong(lastNotificationKey, 0);
        } else {
            lastSync = 0;
        }

        if (System.currentTimeMillis() - lastSync >= HALF_DAY_IN_MILLIS) {

            long deleted = getContentResolver().delete(WeatherColumns.CONTENT_URI, null, null);
            Log.d("CONTENT_QUERY_deleted#", String.valueOf(deleted));

            try {
                final String url = "http://api.openweathermap.org/data/2.5/forecast?id=" + cityId + "&mode=json&units=metric&cnt=14&appid=1f846e7a0e00cf8c2f96dd5e768580fb";
//                final String url = "http://api.openweathermap.org/data/2.5/forecast/daily?id=" + cityId + "&mode=json&units=metric&cnt=14&appid=1f846e7a0e00cf8c2f96dd5e768580fb";
                Log.d("WEATHER_INTENT_SERVICE", "URL#" + url);
                getTodayWeatherFromJson(connectToServer(url));

                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong(lastNotificationKey, System.currentTimeMillis());
                editor.apply();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("URL BUG", e.toString());
            }
        }
    }

    private void getTodayWeatherFromJson(String jsonStr) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_DATE = "dt";
        final String TAG_DATE_TXT = "dt_txt";
        final String TAG_NAME = "name";
        final String TAG_MAIN = "main";
        final String TAG_MAX_TEMP = "temp_max";
        final String TAG_MIN_TEMP = "temp_min";
        final String TAG_HUMIDITY = "humidity";
        final String TAG_WIND_SPEED = "speed";
        final String TAG_PRESSURE = "pressure";
        final String TAG_DEG = "deg";
        final String TAG_CITY = "city";
        final String TAG_WIND = "wind";

        final String TAG_LIST = "list";
        final String TAG_WEATHER = "weather";

        JSONObject forecastJson = new JSONObject(jsonStr);
        JSONObject cityObject = forecastJson.getJSONObject(TAG_CITY);
        String name = cityObject.getString(TAG_NAME);


        JSONArray LIST_ARRAY = forecastJson.getJSONArray(TAG_LIST);//traverse down into the array
        int jsonLength = LIST_ARRAY.length();//get length of the jsonArray

        Time dayTime = new Time();
        dayTime.setToNow();

        // we start at the day returned by local time. Otherwise this is a mess.
        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

        // now we work exclusively in UTC
        dayTime = new Time();

        for(int i = 0; i < jsonLength; i++) {

            // Get the JSON object representing the day
            JSONObject c = LIST_ARRAY.getJSONObject(i);//point to a single row in the jsonArray
            //extract individual items from the json object
//            String id = c.getString(TAG_ID);
//            String name = c.getString(TAG_NAME);
            String date = c.getString(TAG_DATE);
            String date_txt = c.getString(TAG_DATE_TXT);

            JSONObject weatherObject = c.getJSONArray(TAG_WEATHER).getJSONObject(0);
            String main = weatherObject.getString(TAG_MAIN);
            int weatherId = weatherObject.getInt(TAG_ID);

            long dateTime = dayTime.setJulianDay(julianStartDay + i);
//            long dateTime = dayTime.setJulianDay(julianStartDay + i);

            JSONObject windObject = c.getJSONObject(TAG_WIND);
            double windSpeed = windObject.getDouble(TAG_WIND_SPEED);
            double deg = windObject.getDouble(TAG_DEG);

            JSONObject temperatureObject = c.getJSONObject(TAG_MAIN);
            double high = temperatureObject.getDouble(TAG_MAX_TEMP);
            double low = temperatureObject.getDouble(TAG_MIN_TEMP);
            double humidity = temperatureObject.getDouble(TAG_HUMIDITY);
            double pressure = temperatureObject.getDouble(TAG_PRESSURE);

            Log.d("SYNC_DATA", date + " " + name + " " + main + " " + high + " " + low + " " + weatherId + " " + windSpeed + " " + deg + " " + humidity + " " + pressure  + " " + date_txt);
//            storeInTodayWeatherTable(Long.parseLong(date), name, main, high, low, weatherId, windSpeed, deg, humidity, pressure);
            storeInTodayWeatherTable(dateTime, date_txt, name, main, high, low, weatherId, windSpeed, deg, humidity, pressure);
        }
    }

    private void storeInTodayWeatherTable(long date, String date_txt, String name, String main, double high, double low, int weatherId, double windSpeed, double deg, double humidity, double pressure) {
        Log.d("INSERT: ", "starting");
        WeatherContentValues values = new WeatherContentValues();
        values.putDate((int) date);
        values.putDateTxt(date_txt);
        values.putName(name);
        values.putMain(main);
        values.putWeatherId(weatherId);
        values.putMaxTemp((float) high);
        values.putMinTemp((float) low);
        values.putWindSpeed((float) windSpeed);
        values.putDeg((float) deg);
        values.putHumidity((float) humidity);
        values.putPressure((float) pressure);
        Uri uri = values.insert(getContentResolver());
        Log.d("INSERT: ", uri.toString());
    }

    private String connectToServer(String urlConnection) throws Exception{
        Request request = new Request.Builder().url(urlConnection).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();
        Log.d("JSON STRING_DATA", jsonData);
        return jsonData;
    }
}
