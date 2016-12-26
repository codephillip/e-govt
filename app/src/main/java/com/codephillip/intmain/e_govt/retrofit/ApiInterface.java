package com.codephillip.intmain.e_govt.retrofit;

import com.codephillip.intmain.e_govt.mymodel.chapters.Chapters;
import com.codephillip.intmain.e_govt.mymodel.districts.Districts;
import com.codephillip.intmain.e_govt.mymodel.events.Events;
import com.codephillip.intmain.e_govt.mymodel.feedbacks.Feedback;
import com.codephillip.intmain.e_govt.mymodel.ministrys.Ministrys;
import com.codephillip.intmain.e_govt.mymodel.weatherdistricts.Weatherdistricts;
import com.codephillip.intmain.e_govt.mymodel.weathertoday.WeatherToday;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by codephillip on 22/12/16.
 */

public interface ApiInterface {

    @GET("/api/v1/districts?format=json")
    Call<Districts> allDistricts();

    @GET("/api/v1/ministrys?format=json")
    Call<Ministrys> allMinistrys();

    @GET("/api/v1/events?format=json")
    Call<Events> allEvents();

    @GET("/api/v1/chapters?format=json")
    Call<Chapters> allChapters();

    @POST("/api/v1/feedbacks/post")
    Call<Feedback> createFeedback(@Body Feedback feedback);

    @GET("/data/2.5/group?&units=metric")
    Call<Weatherdistricts> allWeatherDistricts(@Query("id") String value, @Query("appid") String appid);

    @GET("/data/2.5/forecast?&mode=json&units=metric&cnt=2")
    Call<WeatherToday> allWeatherToday(@Query("id") String value, @Query("appid") String appid);
}
