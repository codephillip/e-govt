package com.codephillip.intmain.e_govt;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by codephillip on 1/4/16.
 */
public class Utility {
    public static String returnJson(){
        return "{\"day\":\"thursdaycode\",\"lunch\":\"food\"}";
    }

    public static void picassoLoader(Context context, ImageView imageView, String url){
        Log.d("PICASSO", "loading image");
        Picasso.with(context)
                .load(url)
//                .load("http://192.168.56.1/images/ahagzjsozh.jpg")
                        //.resize(30,30)
                .placeholder(R.drawable.nav_image)
                .error(R.drawable.nav_image)
                .into(imageView);
    }

    public static int randInt(int min, int max) {
        int randomNum = min + (int)(Math.random() * ((max - min) + 1));
        Log.d("RANDOM", String.valueOf(randomNum));
        return randomNum;
    }
}
