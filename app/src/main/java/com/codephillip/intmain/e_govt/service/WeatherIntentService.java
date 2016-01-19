package com.codephillip.intmain.e_govt.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.text.format.Time;
import android.util.Log;

import com.codephillip.intmain.e_govt.provider.weather.WeatherContentValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherIntentService extends IntentService {

    public WeatherIntentService() {
        super("WeatherIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("WEATHER_INTENT_SERVICE", "ONHANDLE_INTENT");

        double cityId = intent.getIntExtra("cityId", 233114);
        Log.d("WEATHER_INTENT_SERVICE", "city Id#"+cityId);


        try {
            getTodayWeatherFromJson(connectToServer("http://api.openweathermap.org/data/2.5/forecast?id="+ cityId +"&mode=json&units=metric&cnt=7&appid=1f846e7a0e00cf8c2f96dd5e768580fb"));
//            getTodayWeatherFromJson(connectToServer("http://api.openweathermap.org/data/2.5/forecast?id=233114&mode=json&units=metric&cnt=7&appid=1f846e7a0e00cf8c2f96dd5e768580fb"));
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("URL BUG", e.toString());
        }

    }

    private void getTodayWeatherFromJson(String jsonStr) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_DATE = "dt";
        final String TAG_NAME = "name";
        final String TAG_MAIN = "main";
        final String TAG_MAX_TEMP = "temp_max";
        final String TAG_MIN_TEMP = "temp_min";
        final String TAG_CITY = "city";

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

            JSONObject weatherObject = c.getJSONArray(TAG_WEATHER).getJSONObject(0);
            String main = weatherObject.getString(TAG_MAIN);
            int weatherId = weatherObject.getInt(TAG_ID);

            long dateTime = dayTime.setJulianDay(julianStartDay + i);

            JSONObject temperatureObject = c.getJSONObject(TAG_MAIN);
            double high = temperatureObject.getDouble(TAG_MAX_TEMP);
            double low = temperatureObject.getDouble(TAG_MIN_TEMP);

            Log.d("SYNC_DATA", date + " " + name + " " + main + " " + high + " " + low + " " + weatherId);
            storeInTodayWeatherTable(dateTime, name, main, high, low, weatherId);
        }
    }

    private void storeInTodayWeatherTable(long date, String name, String main, double high, double low, int weatherId) {
        Log.d("INSERT: ", "starting");
        WeatherContentValues values = new WeatherContentValues();
        values.putDate((int) date);
        values.putName(name);
        values.putMain(main);
        values.putWeatherId(weatherId);
        values.putMaxTemp((float) high);
        values.putMinTemp((float) low);
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
