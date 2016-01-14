package com.codephillip.intmain.e_govt.provider.ministries;

import android.net.Uri;
import android.provider.BaseColumns;

import com.codephillip.intmain.e_govt.provider.MyContentProvider;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsColumns;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesColumns;

/**
 * Columns for the {@code ministries} table.
 */
public class MinistriesColumns implements BaseColumns {
    public static final String TABLE_NAME = "ministries";
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ID_MINISTRIES = "id_ministries";

    public static final String MINISTRY_NAME = "ministry_name";

    public static final String IMAGE = "image";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ID_MINISTRIES,
            MINISTRY_NAME,
            IMAGE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ID_MINISTRIES) || c.contains("." + ID_MINISTRIES)) return true;
            if (c.equals(MINISTRY_NAME) || c.contains("." + MINISTRY_NAME)) return true;
            if (c.equals(IMAGE) || c.contains("." + IMAGE)) return true;
        }
        return false;
    }

}
