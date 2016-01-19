package com.codephillip.intmain.e_govt.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codephillip.intmain.e_govt.R;
import com.codephillip.intmain.e_govt.Utility;
import com.codephillip.intmain.e_govt.provider.todayweather.TodayweatherColumns;
import com.codephillip.intmain.e_govt.service.WeatherIntentService;
import com.codephillip.intmain.e_govt.weather.DistrictWeatherActivity;

/**
 * Created by codephillip on 12/26/15.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private static Context context;
    View cardview;
    Cursor dataCursor;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView district;
        public TextView weatherName;
        public TextView temp;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            view = v;
            district = (TextView) v.findViewById(R.id.district);
            weatherName = (TextView) v.findViewById(R.id.weather_name);
            temp = (TextView) v.findViewById(R.id.temp);
            imageView = (ImageView) v.findViewById(R.id.image);

//            v.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    Log.d("RECYCLER", "CLICK");
//                    context.startService(new Intent(context, WeatherIntentService.class).putExtra("cityId", ));
//                    context.startActivity(new Intent(context, DistrictWeatherActivity.class).putExtra("districtWeatherIntent", district.getText()));
//                }
//            });
        }
    }
    public WeatherAdapter(Context context, Cursor cursor) {
        WeatherAdapter.context = context;
        dataCursor = cursor;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        cardview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_districts_row, parent, false);
//                .inflate(R.layout.weather_cardview, parent, false);
        return new ViewHolder(cardview);
    }

    public Cursor swapCursor(Cursor cursor) {
        if (dataCursor == cursor) {
            return null;
        }
        Cursor oldCursor = dataCursor;
        this.dataCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dataCursor.moveToPosition(position);

        final String district = dataCursor.getString(dataCursor.getColumnIndex(TodayweatherColumns.NAME));
        final String weatherName = dataCursor.getString(dataCursor.getColumnIndex(TodayweatherColumns.MAIN));
        final double temp = dataCursor.getDouble(dataCursor.getColumnIndex(TodayweatherColumns.MAX_TEMP));
        final int weatherId = dataCursor.getInt(dataCursor.getColumnIndex(TodayweatherColumns.WEATHER_ID));
        final int cityId = dataCursor.getInt(dataCursor.getColumnIndex(TodayweatherColumns.CITY_ID));
        Log.d("STRETCH_ADAPTER", district + "#" + weatherName + "#" + temp + "#" + weatherId  + "#" + cityId);

        holder.district.setText(district);
        holder.weatherName.setText(weatherName);
//        int tempInt = (int) temp;
//        Log.d("STRETCH_ADAPTER", district + "#" + weatherName + "#" + tempInt);
        holder.temp.setText(Utility.formatTemperature(context,temp));
        holder.imageView.setImageResource(Utility.getArtResourceForWeatherCondition(
                dataCursor.getInt(dataCursor.getColumnIndex(TodayweatherColumns.WEATHER_ID))));
//        Utility.picassoLoader(context, holder.imageView, image);

        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("RECYCLER", "CLICK");
                context.startService(new Intent(context, WeatherIntentService.class).putExtra("cityId", cityId).putExtra("districtWeatherIntent", district));
                context.startActivity(new Intent(context, DistrictWeatherActivity.class).putExtra("districtWeatherIntent", district));
            }
        });
    }
    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }
}