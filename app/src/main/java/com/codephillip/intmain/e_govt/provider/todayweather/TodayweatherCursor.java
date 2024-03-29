package com.codephillip.intmain.e_govt.provider.todayweather;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code todayweather} table.
 */
public class TodayweatherCursor extends AbstractCursor implements TodayweatherModel {
    public TodayweatherCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(TodayweatherColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getDate() {
        Integer res = getIntegerOrNull(TodayweatherColumns.DATE);
        return res;
    }

    /**
     * Get the {@code weather_id} value.
     */
    public int getWeatherId() {
        Integer res = getIntegerOrNull(TodayweatherColumns.WEATHER_ID);
        if (res == null)
            throw new NullPointerException("The value of 'weather_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code city_id} value.
     */
    public int getCityId() {
        Integer res = getIntegerOrNull(TodayweatherColumns.CITY_ID);
        if (res == null)
            throw new NullPointerException("The value of 'city_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        String res = getStringOrNull(TodayweatherColumns.NAME);
        return res;
    }

    /**
     * Get the {@code main} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMain() {
        String res = getStringOrNull(TodayweatherColumns.MAIN);
        return res;
    }

    /**
     * Get the {@code max_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getMaxTemp() {
        Double res = getDoubleOrNull(TodayweatherColumns.MAX_TEMP);
        return res;
    }

    /**
     * Get the {@code min_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getMinTemp() {
        Double res = getDoubleOrNull(TodayweatherColumns.MIN_TEMP);
        return res;
    }
}
