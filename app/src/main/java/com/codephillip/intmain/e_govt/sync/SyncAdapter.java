package com.codephillip.intmain.e_govt.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesContentValues;

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
//    public static final int SYNC_INTERVAL = 60 * 180;
    public static final int SYNC_INTERVAL = 15 * 1;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;
    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    private static final int WEATHER_NOTIFICATION_ID = 3004;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d("SYNCADAPTER", "ONPERFORMSYNC");
//        notifyWeather();

        try {
            int k;
            for (k=0; k<2 ; k++){
                if (k == 0){
                    getMinistriesDataFromJson(connectToServer("http://192.168.56.1/lynda-php/egovtapi.php/ministries?transform=1"));
                } else if (k == 1){
//                    getBbnFromJson(connectToServer("http://192.168.56.1/lynda-php/apibbn.php"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("URL BUG", e.toString());
        }
    }


    private void getMinistriesDataFromJson(String forecastJsonStr)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String TAG_ID = "id";
        final String TAG_MINISTRY_NAME = "ministry_name";
        final String TAG_IMAGE = "image";
        final String TAG_TITLE = "ministries";

        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray nutriArray = forecastJson.getJSONArray(TAG_TITLE);//traverse down into the array
        int jsonLength = nutriArray.length();//get lenght of the jsonArray

        for(int i = 0; i < jsonLength; i++) {

            // Get the JSON object representing the day
            JSONObject c = nutriArray.getJSONObject(i);//point to a single row in the jsonArray
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

//    private void getBbnFromJson(String BbnJsonString)  throws JSONException {
//
//        // These are the names of the JSON objects that need to be extracted.
//        final String TAG_ID = "id";
//        final String TAG_TIPS = "tips";
//        final String TAG_BARCODE = "barcode";
//        final String TAG_TITLE = "title";
//        final String TAG_BC = "bc";
//        final String TAG_URL = "url";
//
//        JSONObject forecastJson = new JSONObject(BbnJsonString);
//        JSONArray nutriArray = forecastJson.getJSONArray(TAG_TITLE);//traverse down into the array
//        int jsonLength = nutriArray.length();//get length of the jsonArray
//
//        for(int i = 0; i < jsonLength; i++) {
//
//            // Get the JSON object representing the day
//            JSONObject c = nutriArray.getJSONObject(i);//point to a single row in the jsonArray
//            //extract individual items from the json object
//            String id = c.getString(TAG_ID);
//            String tips = c.getString(TAG_TIPS);
//            String barcode = c.getString(TAG_BARCODE);
//            String bc = c.getString(TAG_BC);
//            String url = c.getString(TAG_URL);
//
//            Log.d("FEEDBA", id+ " "+tips + " "+barcode+ " "+bc+ " "+url);
//
//            storeInBbnTable(tips, bc, barcode, url);
//        }
//    }

        private void storeInMinistriesTable(String id, String ministry_name, String image){
            Log.d("INSERT: ", "starting");
            MinistriesContentValues values = new MinistriesContentValues();
            values.putMinistryName(ministry_name);
            values.putImage(image);
            final Uri uri = values.insert(getContext().getContentResolver());
            Log.d("INSERT: ", "inserting"+uri.toString());
        }

//        private void storeInFixtureTable(String date, String status, String homeTeamName, String awayTeamName, String goalsHomeTeam, String goalsAwayTeam, int leagueNo){
//            Log.d("INSERT: ", "starting");
//            ContentValues values = new ContentValues();
//            values.put(SoccerContract.FixturesTable.TAG_HOME_TEAM_NAME, homeTeamName);
//            values.put(SoccerContract.FixturesTable.TAG_AWAY_TEAM_NAME, awayTeamName);
//            values.put(SoccerContract.FixturesTable.TAG_DATE, date);
//            values.put(SoccerContract.FixturesTable.TAG_GOALS_HOME_TEAM, goalsHomeTeam);
//            values.put(SoccerContract.FixturesTable.TAG_GOALS_AWAY_TEAM, goalsAwayTeam);
//            values.put(SoccerContract.FixturesTable.TAG_STATUS, status);
//            values.put(SoccerContract.FixturesTable.TAG_LEAGUE_NO, String.valueOf(leagueNo));
//            Uri uri = getContext().getContentResolver().insert(SoccerContract.FixturesTable.CONTENT_URI, values);
//            Log.d("INSERT: ", "inserting"+uri.toString());
//        }


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

//            onAccountCreated(newAccount, context);
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
//        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }

//    private void notifyWeather() {
//        Context context = getContext();
//        //checking the last update and notify if it' the first of the day
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        String lastNotificationKey = context.getString(R.string.pref_last_notification);
//        long lastSync = prefs.getLong(lastNotificationKey, 0);
//
//        if (System.currentTimeMillis() - lastSync >= DAY_IN_MILLIS) {
//            // Last sync was more than 1 day ago, let's send a notification with the weather.
//            String locationQuery = Utility.getPreferredLocation(context);
//
//            Uri weatherUri = WeatherContract.WeatherEntry.buildWeatherLocationWithDate(locationQuery, System.currentTimeMillis());
//
//            // we'll query our contentProvider, as always
//            Cursor cursor = context.getContentResolver().query(weatherUri, NOTIFY_WEATHER_PROJECTION, null, null, null);
//
//            if (cursor.moveToFirst()) {
//                int weatherId = cursor.getInt(INDEX_WEATHER_ID);
//                double high = cursor.getDouble(INDEX_MAX_TEMP);
//                double low = cursor.getDouble(INDEX_MIN_TEMP);
//                String desc = cursor.getString(INDEX_SHORT_DESC);
//
//                int iconId = Utility.getIconResourceForWeatherCondition(weatherId);
//                String title = context.getString(R.string.app_name);
//
//                // Define the text of the forecast.
//                String contentText = String.format(context.getString(R.string.format_notification),
//                        desc,
//                        Utility.formatTemperature(context, high),
//                        Utility.formatTemperature(context, low));
//
//                // NotificationCompatBuilder is a very convenient way to build backward-compatible
//                // notifications.  Just throw in some data.
//                NotificationCompat.Builder mBuilder =
//                        new NotificationCompat.Builder(getContext())
//                                .setSmallIcon(iconId)
//                                .setContentTitle(title)
//                                .setContentText(contentText);
//
//                // Make something interesting happen when the user clicks on the notification.
//                // In this case, opening the app is sufficient.
//                Intent resultIntent = new Intent(context, MainActivity.class);
//
//                // The stack builder object will contain an artificial back stack for the
//                // started Activity.
//                // This ensures that navigating backward from the Activity leads out of
//                // your application to the Home screen.
//                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//                stackBuilder.addNextIntent(resultIntent);
//                PendingIntent resultPendingIntent =
//                        stackBuilder.getPendingIntent(
//                                0,
//                                PendingIntent.FLAG_UPDATE_CURRENT
//                        );
//                mBuilder.setContentIntent(resultPendingIntent);
//
//                NotificationManager mNotificationManager =
//                        (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
//                // WEATHER_NOTIFICATION_ID allows you to update the notification later on.
//                mNotificationManager.notify(WEATHER_NOTIFICATION_ID, mBuilder.build());
//
//
//                //refreshing last sync
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putLong(lastNotificationKey, System.currentTimeMillis());
//                editor.commit();
//            }
//        }
//    }

//    private void notifyWeather() {
//        Context context = getContext();
//        //checking the last update and notify if it' the first of the day
////        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
////        String lastNotificationKey = context.getString(R.string.pref_last_notification);
////        long lastSync = prefs.getLong(lastNotificationKey, 0);
//
////        if (System.currentTimeMillis() - lastSync >= DAY_IN_MILLIS) {
//        if (true) {
//            // Last sync was more than 1 day ago, let's send a notification with the weather.
////            String locationQuery = Utility.getPreferredLocation(context);
////
////            Uri weatherUri = WeatherContract.WeatherEntry.buildWeatherLocationWithDate(locationQuery, System.currentTimeMillis());
////
////            // we'll query our contentProvider, as always
////            Cursor cursor = context.getContentResolver().query(weatherUri, NOTIFY_WEATHER_PROJECTION, null, null, null);
////
////            if (cursor.moveToFirst()) {
////                int weatherId = cursor.getInt(INDEX_WEATHER_ID);
////                double high = cursor.getDouble(INDEX_MAX_TEMP);
////                double low = cursor.getDouble(INDEX_MIN_TEMP);
////                String desc = cursor.getString(INDEX_SHORT_DESC);
////
////                int iconId = Utility.getIconResourceForWeatherCondition(weatherId);
//            int iconId = R.drawable.ic_launcher;
//            String title = context.getString(R.string.app_name);
////
////                // Define the text of the forecast.
////                String contentText = String.format(context.getString(R.string.format_notification),
////                        desc,
////                        Utility.formatTemperature(context, high),
////                        Utility.formatTemperature(context, low));
//            String contentText = "This is a notification. Click this";
//
//            // NotificationCompatBuilder is a very convenient way to build backward-compatible
//            // notifications.  Just throw in some data.
//            NotificationCompat.Builder mBuilder =
//                    new NotificationCompat.Builder(getContext())
//                            .setSmallIcon(iconId)
//                            .setContentTitle(title)
//                            .setContentText(contentText);
//
//            // Make something interesting happen when the user clicks on the notification.
//            // In this case, opening the app is sufficient.
//            Intent resultIntent = new Intent(context, MainActivity.class);
//
//            // The stack builder object will contain an artificial back stack for the
//            // started Activity.
//            // This ensures that navigating backward from the Activity leads out of
//            // your application to the Home screen.
//            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//            stackBuilder.addNextIntent(resultIntent);
//            PendingIntent resultPendingIntent =
//                    stackBuilder.getPendingIntent(
//                            0,
//                            PendingIntent.FLAG_UPDATE_CURRENT
//                    );
//            mBuilder.setContentIntent(resultPendingIntent);
//
//            NotificationManager mNotificationManager =
//                    (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
//            // WEATHER_NOTIFICATION_ID allows you to update the notification later on.
//            mNotificationManager.notify(WEATHER_NOTIFICATION_ID, mBuilder.build());
//
//
//            //refreshing last sync
////                SharedPreferences.Editor editor = prefs.edit();
////                editor.putLong(lastNotificationKey, System.currentTimeMillis());
////                editor.commit();
//        }
//    }
}

