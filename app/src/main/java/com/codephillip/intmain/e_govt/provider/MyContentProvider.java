package com.codephillip.intmain.e_govt.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.codephillip.intmain.e_govt.BuildConfig;
import com.codephillip.intmain.e_govt.provider.base.BaseContentProvider;
import com.codephillip.intmain.e_govt.provider.chapters.ChaptersColumns;
import com.codephillip.intmain.e_govt.provider.districts.DistrictsColumns;
import com.codephillip.intmain.e_govt.provider.events.EventsColumns;
import com.codephillip.intmain.e_govt.provider.ministries.MinistriesColumns;

import java.util.Arrays;

public class MyContentProvider extends BaseContentProvider {
    private static final String TAG = MyContentProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.codephillip.intmain.e_govt.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_CHAPTERS = 0;
    private static final int URI_TYPE_CHAPTERS_ID = 1;

    private static final int URI_TYPE_DISTRICTS = 2;
    private static final int URI_TYPE_DISTRICTS_ID = 3;

    private static final int URI_TYPE_EVENTS = 4;
    private static final int URI_TYPE_EVENTS_ID = 5;

    private static final int URI_TYPE_MINISTRIES = 6;
    private static final int URI_TYPE_MINISTRIES_ID = 7;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, ChaptersColumns.TABLE_NAME, URI_TYPE_CHAPTERS);
        URI_MATCHER.addURI(AUTHORITY, ChaptersColumns.TABLE_NAME + "/#", URI_TYPE_CHAPTERS_ID);
        URI_MATCHER.addURI(AUTHORITY, DistrictsColumns.TABLE_NAME, URI_TYPE_DISTRICTS);
        URI_MATCHER.addURI(AUTHORITY, DistrictsColumns.TABLE_NAME + "/#", URI_TYPE_DISTRICTS_ID);
        URI_MATCHER.addURI(AUTHORITY, EventsColumns.TABLE_NAME, URI_TYPE_EVENTS);
        URI_MATCHER.addURI(AUTHORITY, EventsColumns.TABLE_NAME + "/#", URI_TYPE_EVENTS_ID);
        URI_MATCHER.addURI(AUTHORITY, MinistriesColumns.TABLE_NAME, URI_TYPE_MINISTRIES);
        URI_MATCHER.addURI(AUTHORITY, MinistriesColumns.TABLE_NAME + "/#", URI_TYPE_MINISTRIES_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return MySQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_CHAPTERS:
                return TYPE_CURSOR_DIR + ChaptersColumns.TABLE_NAME;
            case URI_TYPE_CHAPTERS_ID:
                return TYPE_CURSOR_ITEM + ChaptersColumns.TABLE_NAME;

            case URI_TYPE_DISTRICTS:
                return TYPE_CURSOR_DIR + DistrictsColumns.TABLE_NAME;
            case URI_TYPE_DISTRICTS_ID:
                return TYPE_CURSOR_ITEM + DistrictsColumns.TABLE_NAME;

            case URI_TYPE_EVENTS:
                return TYPE_CURSOR_DIR + EventsColumns.TABLE_NAME;
            case URI_TYPE_EVENTS_ID:
                return TYPE_CURSOR_ITEM + EventsColumns.TABLE_NAME;

            case URI_TYPE_MINISTRIES:
                return TYPE_CURSOR_DIR + MinistriesColumns.TABLE_NAME;
            case URI_TYPE_MINISTRIES_ID:
                return TYPE_CURSOR_ITEM + MinistriesColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_CHAPTERS:
            case URI_TYPE_CHAPTERS_ID:
                res.table = ChaptersColumns.TABLE_NAME;
                res.idColumn = ChaptersColumns._ID;
                res.tablesWithJoins = ChaptersColumns.TABLE_NAME;
                res.orderBy = ChaptersColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_DISTRICTS:
            case URI_TYPE_DISTRICTS_ID:
                res.table = DistrictsColumns.TABLE_NAME;
                res.idColumn = DistrictsColumns._ID;
                res.tablesWithJoins = DistrictsColumns.TABLE_NAME;
                res.orderBy = DistrictsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_EVENTS:
            case URI_TYPE_EVENTS_ID:
                res.table = EventsColumns.TABLE_NAME;
                res.idColumn = EventsColumns._ID;
                res.tablesWithJoins = EventsColumns.TABLE_NAME;
                res.orderBy = EventsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MINISTRIES:
            case URI_TYPE_MINISTRIES_ID:
                res.table = MinistriesColumns.TABLE_NAME;
                res.idColumn = MinistriesColumns._ID;
                res.tablesWithJoins = MinistriesColumns.TABLE_NAME;
                res.orderBy = MinistriesColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_CHAPTERS_ID:
            case URI_TYPE_DISTRICTS_ID:
            case URI_TYPE_EVENTS_ID:
            case URI_TYPE_MINISTRIES_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
