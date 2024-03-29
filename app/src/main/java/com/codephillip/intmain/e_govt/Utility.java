package com.codephillip.intmain.e_govt;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.CursorLoader;
import android.text.format.Time;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

/**
 * Created by codephillip on 1/4/16.
 */
public class Utility {

    public static final String DATE_FORMAT = "yyyyMMdd";

    public static final String WEATHER_BASE_URL = "http://api.openweathermap.org";

    //http://openweathermap.org/help/city_list.txt
    public static final String[] weatherDistrictIds= {
            "233114,229278,229362,229380,229746,233508,229024,230166,226110,226234",
            "225835,225858,225964,226823,226853,227592,227812,227904,228227,228853",
            "228971,229059,229139,229268,229911,230299,230617,230893,231139,231696",
            "232066,232371,232422,233070,233275,233312,233346,233476,233730,233886",
            "234077,234092,234178,234565,235039,235489,226267,226361,226600,226866",
            "228418,229112,229292,229361,229599,230256,230584,230993,231250,231426",
            "231550,231617,232235,232287,232397,232713,232834,233725,233738,234578",
            "235130,448227,448232"
    };


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
                .transform(new ImageTransCircleTransform())
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
                .transform(new ImageTransCircleTransform())
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
        int randomNum = min + (int) (Math.random() * ((max - min) + 1));
        Log.d("RANDOM", String.valueOf(randomNum));
        return randomNum;
    }

    public static String formatTemperature(Context context, double temperature) {
        // Data stored in Celsius by default.  If user prefers to see in Fahrenheit, convert
        // the values here.
        String suffix = "\u00B0";
        if (!isMetric(context)) {
            temperature = (temperature * 1.8) + 32;
        }
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

    public static String getFriendlyDayString(Context context, long dateInMillis) {
        // The day string for forecast uses the following logic:
        // For today: "Today, June 8"
        // For tomorrow:  "Tomorrow"
        // For the next 5 days: "Wednesday" (just the day name)
        // For all days after that: "Mon Jun 8"

        Time time = new Time();
        time.setToNow();
        long currentTime = System.currentTimeMillis();
        int julianDay = Time.getJulianDay(dateInMillis, time.gmtoff);
        int currentJulianDay = Time.getJulianDay(currentTime, time.gmtoff);

        // If the date we're building the String for is today's date, the format
        // is "Today, June 24"
        if (julianDay == currentJulianDay) {
            String today = context.getString(R.string.today);
            int formatId = R.string.format_full_friendly_date;
            return context.getString(
                    formatId,
                    today,
                    getFormattedMonthDay(context, dateInMillis));
        } else if ( julianDay < currentJulianDay + 7 ) {
            // If the input date is less than a week in the future, just return the day name.
            return getDayName(context, dateInMillis);
        } else {
            // Otherwise, use the form "Mon Jun 3"
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
            return shortenedDateFormat.format(dateInMillis);
        }
    }

    /**
     * Given a day, returns just the name to use for that day.
     * E.g "today", "tomorrow", "wednesday".
     *
     * @param context Context to use for resource localization
     * @param dateInMillis The date in milliseconds
     * @return
     */
    public static String getDayName(Context context, long dateInMillis) {
        // If the date is today, return the localized version of "Today" instead of the actual
        // day name.

        Time t = new Time();
        t.setToNow();
        int julianDay = Time.getJulianDay(dateInMillis, t.gmtoff);
        int currentJulianDay = Time.getJulianDay(System.currentTimeMillis(), t.gmtoff);
        if (julianDay == currentJulianDay) {
            return context.getString(R.string.today);
        } else if ( julianDay == currentJulianDay +1 ) {
            return context.getString(R.string.tomorrow);
        } else {
            Time time = new Time();
            time.setToNow();
            // Otherwise, the format is just the day of the week (e.g "Wednesday".
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            return dayFormat.format(dateInMillis);
        }
    }

    /**
     * Converts db date format to the format "Month day", e.g "June 24".
     * @param context Context to use for resource localization
     * @param dateInMillis The db formatted date string, expected to be of the form specified
     *                in Utility.DATE_FORMAT
     * @return The day in the form of a string formatted "December 6"
     */
    public static String getFormattedMonthDay(Context context, long dateInMillis ) {
        Time time = new Time();
        time.setToNow();
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(Utility.DATE_FORMAT);
        SimpleDateFormat monthDayFormat = new SimpleDateFormat("MMMM dd");
        String monthDayString = monthDayFormat.format(dateInMillis);
        return monthDayString;
    }

    public static String getFormattedWind(Context context, float windSpeed, float degrees) {
        int windFormat;
        if (Utility.isMetric(context)) {
            windFormat = R.string.format_wind_kmh;
        } else {
            windFormat = R.string.format_wind_mph;
            windSpeed = .621371192237334f * windSpeed;
        }

        // From wind direction in degrees, determine compass direction as a string (e.g NW)
        // You know what's fun, writing really long if/else statements with tons of possible
        // conditions.  Seriously, try it!
        String direction = "Unknown";
        if (degrees >= 337.5 || degrees < 22.5) {
            direction = "N";
        } else if (degrees >= 22.5 && degrees < 67.5) {
            direction = "NE";
        } else if (degrees >= 67.5 && degrees < 112.5) {
            direction = "E";
        } else if (degrees >= 112.5 && degrees < 157.5) {
            direction = "SE";
        } else if (degrees >= 157.5 && degrees < 202.5) {
            direction = "S";
        } else if (degrees >= 202.5 && degrees < 247.5) {
            direction = "SW";
        } else if (degrees >= 247.5 && degrees < 292.5) {
            direction = "W";
        } else if (degrees >= 292.5 && degrees < 337.5) {
            direction = "NW";
        }
        return String.format(context.getString(windFormat), windSpeed, direction);
    }

    public static boolean isMetric(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_units_key),
                context.getString(R.string.pref_units_metric))
                .equals(context.getString(R.string.pref_units_metric));
    }

    public static Cursor dbCursor(Uri uri, Context context){
        CursorLoader cursorLoader = new CursorLoader(context, uri, null, null, null, null);
        return cursorLoader.loadInBackground();
    }
}
