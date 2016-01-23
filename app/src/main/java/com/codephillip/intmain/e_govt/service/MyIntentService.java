package com.codephillip.intmain.e_govt.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.codephillip.intmain.e_govt.AnalyticsApplication;
import com.codephillip.intmain.e_govt.Utility;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyIntentService extends IntentService {

    private Tracker mTracker;
    final String TAG = MyIntentService.class.getSimpleName();


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        String name = "CODEPHILLIP";
        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName("ACTIVITY" + name + " # " + TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        try {
            String jsonString = Utility.returnJson(intent.getStringExtra("Date"), intent.getStringExtra("Topic"), intent.getStringExtra("Message"));
//            String jsonString = Utility.returnJson(intent.getStringExtra("Topic"), intent.getStringExtra("Message"));
            Log.d("JSON#", jsonString);
            jsonString = post("http://192.168.56.1/lynda-php/egovtapi.php/feedback", jsonString);
            Log.d("JSON#", jsonString);
            mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("SERVICE")
                .setAction("Sending Feedback "+jsonString)
                .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        Log.d("JSON#", "okhttpCall");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
