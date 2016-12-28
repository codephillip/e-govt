package com.codephillip.intmain.e_govt.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.codephillip.intmain.e_govt.AnalyticsApplication;
import com.codephillip.intmain.e_govt.mymodel.feedbacks.Feedback;
import com.codephillip.intmain.e_govt.retrofit.ApiClient;
import com.codephillip.intmain.e_govt.retrofit.ApiInterface;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import retrofit2.Call;
import retrofit2.Callback;

public class FeedbackIntentService extends IntentService {

    private Tracker mTracker;
    final String TAG = FeedbackIntentService.class.getSimpleName();


    public FeedbackIntentService() {
        super("FeedbackIntentService");
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
            sendFeedback(intent.getStringExtra("Topic"), intent.getStringExtra("Message"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendFeedback(String topic, String message) {
        ApiInterface apiInterface = ApiClient.getClient(ApiClient.BASE_URL).create(ApiInterface.class);
        Feedback feedback = new Feedback(topic, message);
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
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
