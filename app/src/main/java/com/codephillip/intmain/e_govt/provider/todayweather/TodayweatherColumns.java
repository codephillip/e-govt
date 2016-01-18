package com.codephillip.intmain.e_govt.provider.todayweather;

import android.net.Uri;
import android.provider.BaseColumns;

import com.codephillip.intmain.e_govt.provider.MyContentProvider;

/**
 * Columns for the {@code todayweather} table.
 */
public class TodayweatherColumns implements BaseColumns {
    public static final String TABLE_NAME = "todayweather";
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String DATE = "date";

    public static final String NAME = "name";

    public static final String MAIN = "main";

    public static final String MAX_TEMP = "max_temp";

    public static final String MIN_TEMP = "min_temp";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            DATE,
            NAME,
            MAIN,
            MAX_TEMP,
            MIN_TEMP
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(MAIN) || c.contains("." + MAIN)) return true;
            if (c.equals(MAX_TEMP) || c.contains("." + MAX_TEMP)) return true;
            if (c.equals(MIN_TEMP) || c.contains("." + MIN_TEMP)) return true;
        }
        return false;
    }

}
