package com.codephillip.intmain.e_govt.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.codephillip.intmain.e_govt.Utility;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            String jsonString = Utility.returnJson(intent.getStringExtra("Date"), intent.getStringExtra("Topic"), intent.getStringExtra("Message"));
//            String jsonString = Utility.returnJson(intent.getStringExtra("Topic"), intent.getStringExtra("Message"));
            Log.d("JSON#", jsonString);
            jsonString = post("http://192.168.56.1/lynda-php/egovtapi.php/feedback", jsonString);
            Log.d("JSON#", jsonString);
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
