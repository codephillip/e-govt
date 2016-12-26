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
        Integer res = getIntegerOrNull(WeatherColumns.DATE);
        return res;
    }

    /**
     * Get the {@code date_txt} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDateTxt() {
        String res = getStringOrNull(WeatherColumns.DATE_TXT);
        return res;
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
    public Integer getHumidity() {
        Integer res = getIntegerOrNull(WeatherColumns.HUMIDITY);
        return res;
    }

    /**
     * Get the {@code wind_speed} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getWindSpeed() {
        Double res = getDoubleOrNull(WeatherColumns.WIND_SPEED);
        return res;
    }

    /**
     * Get the {@code max_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getMaxTemp() {
        Double res = getDoubleOrNull(WeatherColumns.MAX_TEMP);
        return res;
    }

    /**
     * Get the {@code min_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getMinTemp() {
        Double res = getDoubleOrNull(WeatherColumns.MIN_TEMP);
        return res;
    }

    /**
     * Get the {@code pressure} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getPressure() {
        Double res = getDoubleOrNull(WeatherColumns.PRESSURE);
        return res;
    }

    /**
     * Get the {@code deg} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getDeg() {
        Double res = getDoubleOrNull(WeatherColumns.DEG);
        return res;
    }
}
