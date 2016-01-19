package com.codephillip.intmain.e_govt.provider.weather;

import android.database.Cursor;
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
        return getIntegerOrNull(WeatherColumns.DATE);
    }

    /**
     * Get the {@code weather_id} value.
     */
    public int getWeatherId() {
        Integer res = getIntegerOrNull(WeatherColumns.WEATHER_ID);
        if (res == null)
            throw new NullPointerException("The value of 'weather_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        return getStringOrNull(WeatherColumns.NAME);
    }

    /**
     * Get the {@code main} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMain() {
        return getStringOrNull(WeatherColumns.MAIN);
    }

    /**
     * Get the {@code humidity} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getHumidity() {
        return getFloatOrNull(WeatherColumns.HUMIDITY);
    }

    /**
     * Get the {@code wind_speed} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getWindSpeed() {
        return getFloatOrNull(WeatherColumns.WIND_SPEED);
    }

    /**
     * Get the {@code max_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getMaxTemp() {
        return getFloatOrNull(WeatherColumns.MAX_TEMP);
    }

    /**
     * Get the {@code min_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getMinTemp() {
        return getFloatOrNull(WeatherColumns.MIN_TEMP);
    }

    /**
     * Get the {@code pressure} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getPressure() {
        return getFloatOrNull(WeatherColumns.PRESSURE);
    }

    /**
     * Get the {@code deg} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getDeg() {
        return getFloatOrNull(WeatherColumns.DEG);
    }
}
