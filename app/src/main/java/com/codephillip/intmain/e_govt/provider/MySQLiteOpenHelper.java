package com.codephillip.intmain.e_govt.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.codephillip.intmain.e_govt.BuildConfig;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsColumns;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesColumns;
import com.codephillip.intmain.e_govt.provider.todayweather.TodayweatherColumns;
import com.codephillip.intmain.e_govt.provider.weather.WeatherColumns;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = MySQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "egovtdata.db";
    private static final int DATABASE_VERSION = 1;
    private static MySQLiteOpenHelper sInstance;
    private final Context mContext;
    private final MySQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_CHAPTERS = "CREATE TABLE IF NOT EXISTS "
            + ChaptersColumns.TABLE_NAME + " ( "
            + ChaptersColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ChaptersColumns.ID_CHAPTERS + " INTEGER, "
            + ChaptersColumns.DATE + " TEXT, "
            + ChaptersColumns.TITLE + " TEXT, "
            + ChaptersColumns.STORY + " TEXT, "
            + ChaptersColumns.IMAGE + " TEXT, "
            + ChaptersColumns.MINISTRY + " TEXT, "
            + ChaptersColumns.DISTRICT + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_DISTRICTS = "CREATE TABLE IF NOT EXISTS "
            + DistrictsColumns.TABLE_NAME + " ( "
            + DistrictsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DistrictsColumns.ID_DISTRICTS + " INTEGER, "
            + DistrictsColumns.DISTRICT_NAME + " TEXT, "
            + DistrictsColumns.IMAGE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_EVENTS = "CREATE TABLE IF NOT EXISTS "
            + EventsColumns.TABLE_NAME + " ( "
            + EventsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EventsColumns.ID_EVENTS + " INTEGER, "
            + EventsColumns.DATE + " TEXT, "
            + EventsColumns.TITLE + " TEXT, "
            + EventsColumns.STORY + " TEXT, "
            + EventsColumns.IMAGE + " TEXT, "
            + EventsColumns.MINISTRY + " TEXT, "
            + EventsColumns.LOCATION + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_MINISTRIES = "CREATE TABLE IF NOT EXISTS "
            + MinistriesColumns.TABLE_NAME + " ( "
            + MinistriesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MinistriesColumns.ID_MINISTRIES + " INTEGER, "
            + MinistriesColumns.MINISTRY_NAME + " TEXT, "
            + MinistriesColumns.IMAGE + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_TODAYWEATHER = "CREATE TABLE IF NOT EXISTS "
            + TodayweatherColumns.TABLE_NAME + " ( "
            + TodayweatherColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TodayweatherColumns.DATE + " INTEGER, "
            + TodayweatherColumns.WEATHER_ID + " INTEGER NOT NULL, "
            + TodayweatherColumns.NAME + " TEXT, "
            + TodayweatherColumns.MAIN + " TEXT, "
            + TodayweatherColumns.MAX_TEMP + " REAL, "
            + TodayweatherColumns.MIN_TEMP + " REAL "
            + " );";

    public static final String SQL_CREATE_TABLE_WEATHER = "CREATE TABLE IF NOT EXISTS "
            + WeatherColumns.TABLE_NAME + " ( "
            + WeatherColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WeatherColumns.DATE + " INTEGER, "
            + WeatherColumns.WEATHER_ID + " INTEGER NOT NULL, "
            + WeatherColumns.NAME + " TEXT, "
            + WeatherColumns.MAIN + " TEXT, "
            + WeatherColumns.HUMIDITY + " REAL, "
            + WeatherColumns.WIND_SPEED + " REAL, "
            + WeatherColumns.MAX_TEMP + " REAL, "
            + WeatherColumns.MIN_TEMP + " REAL, "
            + WeatherColumns.PRESSURE + " REAL, "
            + WeatherColumns.DEG + " REAL "
            + " );";

    // @formatter:on

    public static MySQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static MySQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static MySQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new MySQLiteOpenHelper(context);
    }

    private MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new MySQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static MySQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new MySQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private MySQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new MySQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_CHAPTERS);
        db.execSQL(SQL_CREATE_TABLE_DISTRICTS);
        db.execSQL(SQL_CREATE_TABLE_EVENTS);
        db.execSQL(SQL_CREATE_TABLE_MINISTRIES);
        db.execSQL(SQL_CREATE_TABLE_TODAYWEATHER);
        db.execSQL(SQL_CREATE_TABLE_WEATHER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
