package com.codephillip.intmain.e_govt.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.format.Time;
import android.util.Log;

import com.codephillip.intmain.e_govt.MainActivity;
import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersContentValues;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsContentValues;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;
import com.codephillip.intmain.e_govt.provider.events.EventsContentValues;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesColumns;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesContentValues;
import com.codephillip.intmain.e_govt.provider.todayweather.TodayweatherColumns;
import com.codephillip.intmain.e_govt.provider.todayweather.TodayweatherContentValues;
import com.codephillip.intmain.e_govt.provider.weather.WeatherColumns;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by codephillip on 11/9/15.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    // Interval at which to sync with the weather, in seconds.
    // 60 seconds (1 minute) * 180 = 3 hours
    public static int SYNC_INTERVAL = 60 * 360;
    public static int SYNC_MINUTE = 60;
    //    public static final int SYNC_INTERVAL = 60 * 720;
    //    public static final int SYNC_INTERVAL = 60 * 180;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;
//    private static final long DAY_IN_MILLIS = 60 * 3;
    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    private static final int WEATHER_NOTIFICATION_ID = 3004;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d("SYNCADAPTER", "ONPERFORMSYNC");

        /////////////////////////////////////////////////
        //TODO # TURN ON THE NETWORK
        ////////////////////////////////////////////////

        //TODO debug notification [ REMOVE ON RELEASE ]
//        notifyWeather();
//
//        try {
//            deleteTables();
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        String baseUrl = "192.168.43.243";
        try {
            int k;
//            for (k=0; k<4 ; k++){
//                if (k == 0){
////                    getMinistriesDataFromJson(connectToServer("http://"+ baseUrl +"/egovt/egovtapi.php/ministries?transform=1"));
//                    getMinistriesDataFromJson(connectToServer("http://www.codephillip.com/egovtapi.php/ministries?transform=1"));
//                } else if (k == 1){
////                    getChaptersFromJson(connectToServer("http://"+ baseUrl +"/egovt/egovtapi.php/chapters?transform=1"));
//                    getChaptersFromJson(connectToServer("http://www.codephillip.com/egovtapi.php/chapters?transform=1"));
//                } else if (k == 2){
////                    getEventsFromJson(connectToServer("http://"+ baseUrl +"/egovt/egovtapi.php/events?transform=1"));
//                    getEventsFromJson(connectToServer("http://www.codephillip.com/egovtapi.php/events?transform=1"));
//                } else if (k == 3){
//                    getDistrictsFromJson(connectToServer("http://192.168.56.1/lynda-php/egovtapi.php/districts?transform=1"));
//                } else if (k == 4){
//                    getTodayWeatherFromJson(connectToServer("http://api.openweathermap.org/data/2.5/group?id=233114,229278,229362,229380,229746,233508,229024,230166,226110,226234,225835,225858,225964,226823,226853,227592,227812,227904,228227,228853,228971,229059,229139,229268,229911,230299,230617,230893,231139,231696,232066,232371,232422,233070,233275,233312,233346,233476,233730,233886,234077,234092,234178,234565,235039,235489,226267226361,226600,226866,228418,229112,229292,229361,229599,230256,230584,230993,231250,231426,231550,231617,232235,232287,232397,232713,232834,233725,233738,234578,235130,448227,448232&units=metric&appid=1f846e7a0e00cf8c2f96dd5e768580fb"));
//                }
//            }

            getDistrictsFromJson(connectToServer("http://192.168.56.1/egovtapi.php/districts?transform=1"));
            notifyWeather();
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

        final String TAG_LIST = "list";
        final String TAG_WEATHER = "weather";

        JSONObject forecastJson = new JSONObject(jsonStr);
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
            String name = c.getString(TAG_NAME);
            String date = c.getString(TAG_DATE);
            int cityId = c.getInt(TAG_ID);


            JSONObject weatherObject = c.getJSONArray(TAG_WEATHER).getJSONObject(0);
            String main = weatherObject.getString(TAG_MAIN);
            int weatherId = weatherObject.getInt(TAG_ID);

            long dateTime = dayTime.setJulianDay(julianStartDay + i);

            JSONObject temperatureObject = c.getJSONObject(TAG_MAIN);
            double high = temperatureObject.getDouble(TAG_MAX_TEMP);
            double low = temperatureObject.getDouble(TAG_MIN_TEMP);

            Log.d("SYNC_DATA", date+" "+name+ " "+main+ " "+high+ " "+low+ " "+weatherId+ " "+cityId);
            storeInTodayWeatherTable(dateTime, name, main, high, low, weatherId, cityId);
        }
    }

    private void storeInTodayWeatherTable(long date, String name, String main, double high, double low, int weatherId, int cityId) {
        Log.d("INSERT: ", "starting");
        TodayweatherContentValues values = new TodayweatherContentValues();
        values.putDate((int) date);
        values.putName(name);
        values.putMain(main);
        values.putWeatherId(weatherId);
        values.putCityId(cityId);
        values.putMaxTemp((float) high);
        values.putMinTemp((float) low);
        Uri uri = values.insert(getContext().getContentResolver());
        Log.d("INSERT: ", uri.toString());
    }

    private void getDistrictsFromJson(String jsonStr) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_DISTRICT = "district";
        final String TAG_REGION = "region";
        final String TAG_TITLE = "districts";

        JSONObject forecastJson = new JSONObject(jsonStr);
        JSONArray jsonArray = forecastJson.getJSONArray(TAG_TITLE);//traverse down into the array
        int jsonLength = jsonArray.length();//get lenght of the jsonArray

        for(int i = 0; i < jsonLength; i++) {
            // Get the JSON object representing the day
            JSONObject c = jsonArray.getJSONObject(i);//point to a single row in the jsonArray
            //extract individual items from the json object
            String id = c.getString(TAG_ID);
//            String region = c.getString(TAG_REGION);
            String district = c.getString(TAG_DISTRICT);

            Log.d("SYNC_DATA", id+" "+district);
            storeInDistrictTable(id, district);
        }
    }

    private void storeInDistrictTable(String id, String district) {
        Log.d("INSERT: ", "starting");
        DistrictsContentValues values = new DistrictsContentValues();
        values.putDistrictName(district);
//        values.putImage(image);
        values.insert(getContext().getContentResolver());
    }

    private void getEventsFromJson(String jsonStr) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_DATE = "date";
        final String TAG_STORY = "story";
        final String TAG_TITLE = "title";
        final String TAG_EVENTS = "events";
        final String TAG_IMAGE = "image";
        final String TAG_MINISTRY = "ministry";
        final String TAG_LOCATION = "location";

        JSONObject forecastJson = new JSONObject(jsonStr);
        JSONArray jsonArray = forecastJson.getJSONArray(TAG_EVENTS);//traverse down into the array
        int jsonLength = jsonArray.length();//get length of the jsonArray

        for(int i = 0; i < jsonLength; i++) {

            // Get the JSON object representing the day
            JSONObject c = jsonArray.getJSONObject(i);//point to a single row in the jsonArray
            //extract individual items from the json object
            String id = c.getString(TAG_ID);
            String image = c.getString(TAG_IMAGE);
            String ministry = c.getString(TAG_MINISTRY);
            String date = c.getString(TAG_DATE);
            String title = c.getString(TAG_TITLE);
            String story = c.getString(TAG_STORY);
            String location = c.getString(TAG_LOCATION);

            Log.d("SYNC_DATA", id+" "+image+ " "+ministry+ " "+date+ " "+story + " "+ location);
            storeInEventsTable(id, ministry, image, date, story, location, title);
        }
    }

    private void storeInEventsTable(String id, String ministry, String image, String date, String story, String location, String title) {
        EventsContentValues values = new EventsContentValues();
        values.putMinistry(ministry);
        values.putImage(image);
        values.putDate(date);
        values.putTitle(title);
        values.putStory(story);
        values.putLocation(location);
        values.insert(getContext().getContentResolver());
    }

    private void getChaptersFromJson(String jsonStr) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_DATE = "date";
        final String TAG_STORY = "story";
        final String TAG_TITLE = "title";
        final String TAG_CHAPTERS = "chapters";
        final String TAG_IMAGE = "image";
        final String TAG_MINISTRY = "ministry";
        final String TAG_DISTRICT = "district";

        JSONObject forecastJson = new JSONObject(jsonStr);
        JSONArray jsonArray = forecastJson.getJSONArray(TAG_CHAPTERS);//traverse down into the array
        int jsonLength = jsonArray.length();//get length of the jsonArray

        for(int i = 0; i < jsonLength; i++) {

            // Get the JSON object representing the day
            JSONObject c = jsonArray.getJSONObject(i);//point to a single row in the jsonArray
            //extract individual items from the json object
            String id = c.getString(TAG_ID);
            String image = c.getString(TAG_IMAGE);
            String ministry = c.getString(TAG_MINISTRY);
            String title = c.getString(TAG_TITLE);
            String date = c.getString(TAG_DATE);
            String story = c.getString(TAG_STORY);
            String district = c.getString(TAG_DISTRICT);

            Log.d("SYNC_DATA", id+" "+image+ " "+ministry+ " "+date+ " "+story+ " "+district );
            storeInChaptersTable(id, ministry, image, date, story, title, district);
        }
    }

    private void storeInChaptersTable(String id, String ministry, String image, String date, String story, String title, String district) {
        Log.d("INSERT: ", "starting");
        ChaptersContentValues values = new ChaptersContentValues();
        values.putMinistry(ministry);
        values.putDate(date);
        values.putImage(image);
        values.putTitle(title);
        values.putStory(story);
        values.putDistrict(district);
        values.insert(getContext().getContentResolver());
    }


    private void getMinistriesDataFromJson(String jsonStr)
            throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_MINISTRY_NAME = "ministry_name";
        final String TAG_IMAGE = "image";
        final String TAG_TITLE = "ministries";

            JSONObject forecastJson = new JSONObject(jsonStr);
            JSONArray jsonArray = forecastJson.getJSONArray(TAG_TITLE);//traverse down into the array
            int jsonLength = jsonArray.length();//get lenght of the jsonArray

            for(int i = 0; i < jsonLength; i++) {

                // Get the JSON object representing the day
                JSONObject c = jsonArray.getJSONObject(i);//point to a single row in the jsonArray
                //extract individual items from the json object
                String id = c.getString(TAG_ID);
                String image = c.getString(TAG_IMAGE);
                String ministry = c.getString(TAG_MINISTRY_NAME);

                Log.d("SYNC_DATA", id+" "+image+ " "+ministry);
                storeInMinistriesTable(id, ministry, image);
            }
    }

    private String connectToServer(String urlConnection) throws Exception{
        Request request = new Request.Builder().url(urlConnection).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        Log.d("JSON STRING_DATA", jsonData);
        return jsonData;
    }

    private void storeInMinistriesTable(String id, String ministry_name, String image){
        Log.d("INSERT: ", "starting");
        MinistriesContentValues values = new MinistriesContentValues();
        values.putMinistryName(ministry_name);
        values.putImage(image);
        final Uri uri = values.insert(getContext().getContentResolver());
        Log.d("INSERT: ", "inserting"+uri.toString());
    }


    /**
     * Helper method to schedule the sync adapter periodic execution
     */
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }

    /**
     * Helper method to have the sync adapter sync immediately
     *
     * @param context The context used to access the account service
     */
    public static void syncImmediately(Context context) {
        Log.d("SYNC_IMMEDIATELY", "syncImmediately: STARTED");
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if (null == accountManager.getPassword(newAccount)) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
        SyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), true);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        Log.d("SYNCADAPTER", "initializeSyncAdapter: STARTED");
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String syncFrequency = preferences.getString("sync_frequency", "360");
//        Log.d("SYNCADAPTER", "SyncFrequency "+syncFrequency);
//        SYNC_INTERVAL = 60 * Integer.parseInt(syncFrequency);
        getSyncAccount(context);
    }

    private void notifyWeather() {
        Context context = getContext();
        //checking the last update and notify if it' the first of the day
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String lastNotificationKey = context.getString(R.string.pref_last_notification2);
        long lastSync = prefs.getLong(lastNotificationKey, 0);
        boolean displayNotifications = prefs.getBoolean("notifications_new_message", true);

        if (displayNotifications) {

            if (System.currentTimeMillis() - lastSync >= DAY_IN_MILLIS) {
                Cursor cursor = context.getContentResolver().query(TodayweatherColumns.CONTENT_URI, null, null, null, null);
                if (cursor.moveToFirst()) {
                    int weatherId = cursor.getInt(cursor.getColumnIndex(TodayweatherColumns.WEATHER_ID));
                    double high = cursor.getDouble(cursor.getColumnIndex(TodayweatherColumns.MAX_TEMP));
                    double low = cursor.getDouble(cursor.getColumnIndex(TodayweatherColumns.MIN_TEMP));
                    String desc = cursor.getString(cursor.getColumnIndex(TodayweatherColumns.MAIN));

                    int iconId = Utility.getArtResourceForWeatherCondition(weatherId);
                    String title = context.getString(R.string.app_name);

                    // Define the text of the forecast.
                    String contentText = String.format(context.getString(R.string.format_notification),
                            desc,
                            Utility.formatTemperature(context, high),
                            Utility.formatTemperature(context, low));

                    // NotificationCompatBuilder is a very convenient way to build backward-compatible
                    // notifications.  Just throw in some data.
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getContext())
                                    .setSmallIcon(iconId)
                                    .setContentTitle(title)
                                    .setContentText(contentText);

                    // Make something interesting happen when the user clicks on the notification.
                    // In this case, opening the app is sufficient.
                    Intent resultIntent = new Intent(context, MainActivity.class);

                    // The stack builder object will contain an artificial back stack for the
                    // started Activity.
                    // This ensures that navigating backward from the Activity leads out of
                    // your application to the Home screen.
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);

                    NotificationManager mNotificationManager =
                            (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    // WEATHER_NOTIFICATION_ID allows you to update the notification later on.
                    mNotificationManager.notify(WEATHER_NOTIFICATION_ID, mBuilder.build());

//                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Uri notification = Uri.parse(prefs.getString("notifications_new_message_ringtone", "content://settings/system/notification_sound"));
                Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
                r.play();

                if (prefs.getBoolean("notifications_new_message_vibrate", true)){
                    Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    v.vibrate(500);
                }

                    //refreshing last sync
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong(lastNotificationKey, System.currentTimeMillis());
                    editor.apply();
                }
            }
        }
    }

    public void deleteTables(){
        long deleted;
        deleted = getContext().getContentResolver().delete(MinistriesColumns.CONTENT_URI, null, null);
        Log.d("CONTENT_QUERY_deleted#", String.valueOf(deleted));
        deleted = getContext().getContentResolver().delete(ChaptersColumns.CONTENT_URI, null, null);
        Log.d("CONTENT_QUERY_deleted#", String.valueOf(deleted));
        deleted = getContext().getContentResolver().delete(EventsColumns.CONTENT_URI, null, null);
        Log.d("CONTENT_QUERY_deleted#", String.valueOf(deleted));
        deleted = getContext().getContentResolver().delete(TodayweatherColumns.CONTENT_URI, null, null);
        Log.d("CONTENT_QUERY_deleted#", String.valueOf(deleted));
        deleted = getContext().getContentResolver().delete(WeatherColumns.CONTENT_URI, null, null);
        Log.d("CONTENT_QUERY_deleted#", String.valueOf(deleted));
    }
}

