package com.codephillip.intmain.e_govt.provider.weather;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code weather} table.
 */
public class WeatherCursor extends AbstractCursor implements WeatherModel {
    public WeatherCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(WeatherColumns._ID);
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
        Integer res = getIntegerOrNull(WeatherColumns.DATE);
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        String res = getStringOrNull(WeatherColumns.NAME);
        return res;
    }

    /**
     * Get the {@code main} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMain() {
        String res = getStringOrNull(WeatherColumns.MAIN);
        return res;
    }

    /**
     * Get the {@code humidity} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getHumidity() {
        Float res = getFloatOrNull(WeatherColumns.HUMIDITY);
        return res;
    }

    /**
     * Get the {@code wind_speed} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getWindSpeed() {
        Float res = getFloatOrNull(WeatherColumns.WIND_SPEED);
        return res;
    }

    /**
     * Get the {@code max_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getMaxTemp() {
        Float res = getFloatOrNull(WeatherColumns.MAX_TEMP);
        return res;
    }

    /**
     * Get the {@code min_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getMinTemp() {
        Float res = getFloatOrNull(WeatherColumns.MIN_TEMP);
        return res;
    }

    /**
     * Get the {@code pressure} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getPressure() {
        Float res = getFloatOrNull(WeatherColumns.PRESSURE);
        return res;
    }

    /**
     * Get the {@code deg} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getDeg() {
        Float res = getFloatOrNull(WeatherColumns.DEG);
        return res;
    }
}