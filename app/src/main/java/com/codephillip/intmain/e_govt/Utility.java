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
        String retString = "{\"date\":\""+ date +"\",\"topic\":\"" + topic+ "\",\"message\":\""+ message+"\"}";
//        String retString = "{\"topic\":\"" + topic+ "\",\"message\":\""+ message+"\"}";
        return retString;
    }

    public static String returnJson(String topic, String message){
//        String retString = "{\"date\":\""+ date +"\",\"topic\":\"" + topic+ "\",\"message\":\""+ message+"\"}";
        String retString = "{\"topic\":\"" + topic+ "\",\"message\":\""+ message+"\"}";
        return retString;
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
}
