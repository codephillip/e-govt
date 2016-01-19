package com.codephillip.intmain.e_govt;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by codephillip on 1/4/16.
 */
public class Utility {
    public static String returnJson( String date,String topic, String message){
        //        String retString = "{\"topic\":\"" + topic+ "\",\"message\":\""+ message+"\"}";
        return "{\"date\":\""+ date +"\",\"topic\":\"" + topic+ "\",\"message\":\""+ message+"\"}";
    }

    public static String returnJson(String topic, String message){
//        String retString = "{\"date\":\""+ date +"\",\"topic\":\"" + topic+ "\",\"message\":\""+ message+"\"}";
        return "{\"topic\":\"" + topic+ "\",\"message\":\""+ message+"\"}";
    }

    public static void picassoCircleLoader(Context context, ImageView imageView, String url){
        Log.d("PICASSO", "loading image");
        Picasso.with(context)
                .load(url)
//                .load("http://192.168.56.1/images/ahagzjsozh.jpg")
                .resize(150, 150)
                .placeholder(R.drawable.nav_image)
                .error(R.drawable.nav_image)
//                .transform(new CircleTransform())
                .transform(new ImageTrans_CircleTransform())
                .into(imageView);
    }

    public static void picassoCircleLoader(Context context, ImageView imageView, int url){
        Log.d("PICASSO", "loading image");
        Picasso.with(context)
                .load(url)
//                .load("http://192.168.56.1/images/ahagzjsozh.jpg")
                .resize(150, 150)
                .placeholder(R.drawable.nav_image)
                .error(R.drawable.nav_image)
//                .transform(new CircleTransform())
                .transform(new ImageTrans_CircleTransform())
                .into(imageView);
    }

    public static void picassoLoader(Context context, ImageView imageView, String url){
        Log.d("PICASSO", "loading image");
        Picasso.with(context)
                .load(url)
//                .load("http://192.168.56.1/images/ahagzjsozh.jpg")
                .placeholder(R.drawable.nav_image)
                .error(R.drawable.nav_image)
                .into(imageView);
    }

    public static int randInt(int min, int max) {
        int randomNum = min + (int)(Math.random() * ((max - min) + 1));
        Log.d("RANDOM", String.valueOf(randomNum));
        return randomNum;
    }

    public static String formatTemperature(Context context, double temperature) {
        // Data stored in Celsius by default.  If user prefers to see in Fahrenheit, convert
        // the values here.
        String suffix = "\u00B0";
//        if (!isMetric(context)) {
//            temperature = (temperature * 1.8) + 32;
//        }
        // For presentation, assume the user doesn't care about tenths of a degree.
        return String.format(context.getString(R.string.format_temperature), temperature);
    }

    public static int getArtResourceForWeatherCondition(int weatherId) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        }
        return -1;
    }
}
