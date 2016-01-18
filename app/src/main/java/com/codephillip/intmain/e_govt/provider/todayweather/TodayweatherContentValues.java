package com.codephillip.intmain.e_govt.provider.todayweather;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code todayweather} table.
 */
public class TodayweatherContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TodayweatherColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable TodayweatherSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public TodayweatherContentValues putDate(@Nullable Integer value) {
        mContentValues.put(TodayweatherColumns.DATE, value);
        return this;
    }

    public TodayweatherContentValues putDateNull() {
        mContentValues.putNull(TodayweatherColumns.DATE);
        return this;
    }

    public TodayweatherContentValues putName(@Nullable String value) {
        mContentValues.put(TodayweatherColumns.NAME, value);
        return this;
    }

    public TodayweatherContentValues putNameNull() {
        mContentValues.putNull(TodayweatherColumns.NAME);
        return this;
    }

    public TodayweatherContentValues putMain(@Nullable String value) {
        mContentValues.put(TodayweatherColumns.MAIN, value);
        return this;
    }

    public TodayweatherContentValues putMainNull() {
        mContentValues.putNull(TodayweatherColumns.MAIN);
        return this;
    }

    public TodayweatherContentValues putMaxTemp(@Nullable Float value) {
        mContentValues.put(TodayweatherColumns.MAX_TEMP, value);
        return this;
    }

    public TodayweatherContentValues putMaxTempNull() {
        mContentValues.putNull(TodayweatherColumns.MAX_TEMP);
        return this;
    }

    public TodayweatherContentValues putMinTemp(@Nullable Float value) {
        mContentValues.put(TodayweatherColumns.MIN_TEMP, value);
        return this;
    }

    public TodayweatherContentValues putMinTempNull() {
        mContentValues.putNull(TodayweatherColumns.MIN_TEMP);
        return this;
    }
}
