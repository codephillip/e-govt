package com.codephillip.intmain.e_govt.provider.events;

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
 * Columns for the {@code events} table.
 */
public class EventsColumns implements BaseColumns {
    public static final String TABLE_NAME = "events";
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ID_EVENTS = "id_events";

    public static final String DATE = "date";

    public static final String TITLE = "title";

    public static final String STORY = "story";

    public static final String IMAGE = "image";

    public static final String MINISTRY = "ministry";

    public static final String LOCATION = "location";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ID_EVENTS,
            DATE,
            TITLE,
            STORY,
            IMAGE,
            MINISTRY,
            LOCATION
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ID_EVENTS) || c.contains("." + ID_EVENTS)) return true;
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(STORY) || c.contains("." + STORY)) return true;
            if (c.equals(IMAGE) || c.contains("." + IMAGE)) return true;
            if (c.equals(MINISTRY) || c.contains("." + MINISTRY)) return true;
            if (c.equals(LOCATION) || c.contains("." + LOCATION)) return true;
        }
        return false;
    }

}
