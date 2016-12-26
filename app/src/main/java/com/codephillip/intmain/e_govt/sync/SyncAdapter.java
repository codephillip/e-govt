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
import android.util.Log;

import com.codephillip.intmain.e_govt.MainActivity;
import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.mymodel.chapters.Chapter;
import com.codephillip.intmain.e_govt.mymodel.chapters.Chapters;
import com.codephillip.intmain.e_govt.mymodel.districts.District;
import com.codephillip.intmain.e_govt.mymodel.districts.Districts;
import com.codephillip.intmain.e_govt.mymodel.events.Event;
import com.codephillip.intmain.e_govt.mymodel.events.Events;
import com.codephillip.intmain.e_govt.mymodel.feedbacks.Feedback;
import com.codephillip.intmain.e_govt.mymodel.ministrys.Ministry;
import com.codephillip.intmain.e_govt.mymodel.ministrys.Ministrys;
import com.codephillip.intmain.e_govt.mymodel.weatherdistricts.ListWeather;
import com.codephillip.intmain.e_govt.mymodel.weatherdistricts.Weatherdistricts;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersContentValues;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsColumns;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsContentValues;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;
import com.codephillip.intmain.e_govt.provider.events.EventsContentValues;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesColumns;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesContentValues;
import com.codephillip.intmain.e_govt.provider.todayweather.TodayweatherColumns;
import com.codephillip.intmain.e_govt.provider.todayweather.TodayweatherContentValues;
import com.codephillip.intmain.e_govt.provider.weather.WeatherColumns;
import com.codephillip.intmain.e_govt.retrofit.ApiClient;
import com.codephillip.intmain.e_govt.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by codephillip on 11/9/15.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private static final String TAG = SyncAdapter.class.getSimpleName();

    // Interval at which to sync with the server, in seconds.
    // 60 seconds (1 minute) * 60 * 2 = 2 hour
    public static int SYNC_INTERVAL = 60 * 60 * 24;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;
    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    private static final int WEATHER_NOTIFICATION_ID = 3004;
    private ApiInterface apiInterface, apiInterfaceWeather;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d(TAG, "ONPERFORMSYNC");

        apiInterface = ApiClient.getClient(ApiClient.BASE_URL).create(ApiInterface.class);
        apiInterfaceWeather = ApiClient.getClient(ApiClient.WEATHER_BASE_URL).create(ApiInterface.class);

        deleteTables();

        loadDistricts();
//        loadMinistrys();
//        loadEvents();
//        loadChapters();
//        sendFeedback();
//        for (String weatherDistrictId : Utility.weatherDistrictIds) {
//            loadWeatherDistricts(weatherDistrictId);
//        }
    }


    private void sendFeedback() {
        Feedback feedback = new Feedback("dummy title2", "dummy content, dummy content2");
        Call<Feedback> call = apiInterface.createFeedback(feedback);
        call.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, retrofit2.Response<Feedback> response) {
                int statusCode = response.code();
                Feedback feedback1 = response.body();
                Log.d(TAG, "onResponse: #" + feedback1.getTitle() + feedback1.getContent() + statusCode);
            }

            @Override
            public void onFailure(Call<Feedback> call, Throwable t) {

            }
        });
    }

    private void loadChapters() {
        Call<Chapters> call = apiInterface.allChapters();
        call.enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, retrofit2.Response<Chapters> response) {
                Chapters chapters = response.body();
                saveChapters(chapters);
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void saveChapters(Chapters chapters) {
        if (chapters == null)
            throw new NullPointerException("Chapters not found");
        List<Chapter> chapterList = chapters.getChapters();
        for (Chapter chapter : chapterList) {
            Log.d(TAG, "saveChapter: " + chapter.getId() + chapter.getTitle() + chapter.getImage() + chapter.getMinistry().getName() + chapter.getDistrict().getName());
            ChaptersContentValues values = new ChaptersContentValues();
            values.putMinistry(chapter.getMinistry().getName());
            values.putDate(chapter.getDate());
            values.putImage(chapter.getImage());
            values.putTitle(chapter.getTitle());
            values.putStory(chapter.getStory());
            values.putDistrict(chapter.getDistrict().getName());
            values.insert(getContext().getContentResolver());
        }
    }

    private void loadEvents() {
        Call<Events> call = apiInterface.allEvents();
        call.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, retrofit2.Response<Events> response) {
                Events events = response.body();
                storeInEventsTable(events);
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void storeInEventsTable(Events events) {
        if (events == null)
            throw new NullPointerException("Events not found");
        List<Event> eventList = events.getEvents();
        for (Event event : eventList) {
            Log.d(TAG, "saveEvent: " + event.getId() + event.getTitle() + event.getLocation() + event.getMinistry().getName());
            EventsContentValues values = new EventsContentValues();
            values.putMinistry(event.getMinistry().getName());
            values.putImage(event.getImage());
            values.putDate(event.getDate());
            values.putTitle(event.getTitle());
            values.putStory(event.getStory());
            values.putLocation(event.getLocation());
            values.insert(getContext().getContentResolver());
        }
    }

    private void loadMinistrys() {
        Call<Ministrys> call = apiInterface.allMinistrys();
        call.enqueue(new Callback<Ministrys>() {
            @Override
            public void onResponse(Call<Ministrys> call, retrofit2.Response<Ministrys> response) {
                Ministrys ministrys = response.body();
                storeInMinistriesTable(ministrys);
            }

            @Override
            public void onFailure(Call<Ministrys> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void storeInMinistriesTable(Ministrys ministrys) {
        Log.d("INSERT: ", "starting");
        if (ministrys == null)
            throw new NullPointerException("Ministrys not found");
        List<Ministry> ministryList = ministrys.getMinistrys();
        for (Ministry ministry : ministryList) {
            Log.d(TAG, "saveMinistry: " + ministry.getId() + ministry.getName() + ministry.getImage());
            MinistriesContentValues values = new MinistriesContentValues();
            values.putMinistryName(ministry.getName());
            values.putImage(ministry.getImage());
            final Uri uri = values.insert(getContext().getContentResolver());
            Log.d("INSERT: ", "inserting" + uri.toString());
        }
    }


    private void loadDistricts() {
        Call<Districts> call = apiInterface.allDistricts();
        call.enqueue(new Callback<Districts>() {
            @Override
            public void onResponse(Call<Districts> call, retrofit2.Response<Districts> response) {
                Districts ministrys = response.body();
                storeInDistrictTable(ministrys);
            }

            @Override
            public void onFailure(Call<Districts> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void storeInDistrictTable(Districts districts) {
        Log.d("INSERT: ", "starting");
        //todo bulk inserts
        if (districts == null)
            throw new NullPointerException("Districts not found");
        Log.d(TAG, "saveDistrict: #" + districts);
        List<District> districtList = districts.getDistricts();
        for (District district : districtList) {
            Log.d(TAG, "saveDistrict: " + district.getId() + district.getName() + district.getRegion());
            DistrictsContentValues values = new DistrictsContentValues();
            values.putDistrictName(district.getName());
            values.insert(getContext().getContentResolver());
        }
    }

    private void loadWeatherDistricts(String weatherDistrictId) {
        Call<Weatherdistricts> call = apiInterfaceWeather.allWeatherDistricts(weatherDistrictId, "1f846e7a0e00cf8c2f96dd5e768580fb");
        call.enqueue(new Callback<Weatherdistricts>() {
            @Override
            public void onResponse(Call<Weatherdistricts> call, Response<Weatherdistricts> response) {
                Weatherdistricts wd = response.body();
                saveWeatherdistricts(wd);
            }

            @Override
            public void onFailure(Call<Weatherdistricts> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                throw new UnsupportedOperationException("Failed to start");
            }
        });
    }

    private void saveWeatherdistricts(Weatherdistricts wd) {
        if (wd == null)
            throw new NullPointerException("Weatherdistricts not found");
        java.util.List<ListWeather> listWeather = wd.getListWeather();
        for (ListWeather weather : listWeather) {
            Log.d(TAG, "saveWeatherdistricts: " + weather.getName() + weather.getMain().getTempMin() + weather.getMain().getTempMax() + weather.getWeather().get(0).getMain());
            //todayweather table is used by Weatherdistricts
            //while weather table is used by WeatherToday
            TodayweatherContentValues values = new TodayweatherContentValues();
            values.putDate(weather.getDt());
            values.putName(weather.getName());
            values.putMain(weather.getWeather().get(0).getMain());
            values.putWeatherId(weather.getWeather().get(0).getId());
            values.putCityId(weather.getId());
            values.putMaxTemp(weather.getMain().getTempMax());
            values.putMinTemp(weather.getMain().getTempMin());
            Uri uri = values.insert(getContext().getContentResolver());
            Log.d("INSERT: ", uri.toString());
        }
    }

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

    public static void syncImmediately(Context context) {
        Log.d("SYNC_IMMEDIATELY", "syncImmediately: STARTED");
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

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

                    Intent resultIntent = new Intent(context, MainActivity.class);
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
                    mNotificationManager.notify(WEATHER_NOTIFICATION_ID, mBuilder.build());
                    Uri notification = Uri.parse(prefs.getString("notifications_new_message_ringtone", "content://settings/system/notification_sound"));
                    Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
                    r.play();

                    if (prefs.getBoolean("notifications_new_message_vibrate", true)) {
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

    public void deleteTables() {
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
        deleted = getContext().getContentResolver().delete(DistrictsColumns.CONTENT_URI, null, null);
        Log.d("CONTENT_QUERY_deleted#", String.valueOf(deleted));
    }
}

