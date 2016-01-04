package com.codephillip.intmain.e_govt;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        List<DataObject> objList = new ArrayList<DataObject>();
        objList.add(new DataObject(0, "zero"));
        objList.add(new DataObject(1, "one"));
        objList.add(new DataObject(2, "two"));

        // Convert the object to a JSON string
        String json = new Gson().toJson(objList);
//        System.out.println(json);
        Log.d("JSON", json);
//        try {
//            String jsonString = post("http://192.168.56.1/lynda-php/api.php/api1", "{\"day\":\"thursdaycode\"}");
//            Log.d("JSON", jsonString);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
