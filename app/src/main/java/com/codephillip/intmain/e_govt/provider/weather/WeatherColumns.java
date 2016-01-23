package com.codephillip.intmain.e_govt.provider.weather;

import android.net.Uri;
import android.provider.BaseColumns;

import com.codephillip.intmain.e_govt.provider.MyContentProvider;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsColumns;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesColumns;
import com.codephillip.intmain.e_govt.provider.todayweather.TodayweatherColumns;
import com.codephillip.intmain.e_govt.provider.weather.WeatherColumns;

/**
 * Columns for the {@code weather} table.
 */
public class WeatherColumns implements BaseColumns {
    public static final String TABLE_NAME = "weather";
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String DATE = "date";

    public static final String DATE_TXT = "date_txt";

    public static final String WEATHER_ID = "weather_id";

    public static final String NAME = "name";

    public static final String MAIN = "main";

    public static final String HUMIDITY = "humidity";

    public static final String WIND_SPEED = "wind_speed";

    public static final String MAX_TEMP = "max_temp";

    public static final String MIN_TEMP = "min_temp";

    public static final String PRESSURE = "pressure";

    public static final String DEG = "deg";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            DATE,
            DATE_TXT,
            WEATHER_ID,
            NAME,
            MAIN,
            HUMIDITY,
            WIND_SPEED,
            MAX_TEMP,
            MIN_TEMP,
            PRESSURE,
            DEG
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(DATE_TXT) || c.contains("." + DATE_TXT)) return true;
            if (c.equals(WEATHER_ID) || c.contains("." + WEATHER_ID)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(MAIN) || c.contains("." + MAIN)) return true;
            if (c.equals(HUMIDITY) || c.contains("." + HUMIDITY)) return true;
            if (c.equals(WIND_SPEED) || c.contains("." + WIND_SPEED)) return true;
            if (c.equals(MAX_TEMP) || c.contains("." + MAX_TEMP)) return true;
            if (c.equals(MIN_TEMP) || c.contains("." + MIN_TEMP)) return true;
            if (c.equals(PRESSURE) || c.contains("." + PRESSURE)) return true;
            if (c.equals(DEG) || c.contains("." + DEG)) return true;
        }
        return false;
    }

}
