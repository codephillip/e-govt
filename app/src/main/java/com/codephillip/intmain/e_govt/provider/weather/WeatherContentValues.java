package com.codephillip.intmain.e_govt.provider.weather;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code weather} table.
 */
public class WeatherContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return WeatherColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable WeatherSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public WeatherContentValues putDate(@Nullable Integer value) {
        mContentValues.put(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherContentValues putDateNull() {
        mContentValues.putNull(WeatherColumns.DATE);
        return this;
    }

    public WeatherContentValues putDateTxt(@Nullable String value) {
        mContentValues.put(WeatherColumns.DATE_TXT, value);
        return this;
    }

    public WeatherContentValues putDateTxtNull() {
        mContentValues.putNull(WeatherColumns.DATE_TXT);
        return this;
    }

    public WeatherContentValues putWeatherId(int value) {
        mContentValues.put(WeatherColumns.WEATHER_ID, value);
        return this;
    }


    public WeatherContentValues putName(@Nullable String value) {
        mContentValues.put(WeatherColumns.NAME, value);
        return this;
    }

    public WeatherContentValues putNameNull() {
        mContentValues.putNull(WeatherColumns.NAME);
        return this;
    }

    public WeatherContentValues putMain(@Nullable String value) {
        mContentValues.put(WeatherColumns.MAIN, value);
        return this;
    }

    public WeatherContentValues putMainNull() {
        mContentValues.putNull(WeatherColumns.MAIN);
        return this;
    }

    public WeatherContentValues putHumidity(@Nullable Float value) {
        mContentValues.put(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherContentValues putHumidityNull() {
        mContentValues.putNull(WeatherColumns.HUMIDITY);
        return this;
    }

    public WeatherContentValues putWindSpeed(@Nullable Float value) {
        mContentValues.put(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherContentValues putWindSpeedNull() {
        mContentValues.putNull(WeatherColumns.WIND_SPEED);
        return this;
    }

    public WeatherContentValues putMaxTemp(@Nullable Float value) {
        mContentValues.put(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherContentValues putMaxTempNull() {
        mContentValues.putNull(WeatherColumns.MAX_TEMP);
        return this;
    }

    public WeatherContentValues putMinTemp(@Nullable Float value) {
        mContentValues.put(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherContentValues putMinTempNull() {
        mContentValues.putNull(WeatherColumns.MIN_TEMP);
        return this;
    }

    public WeatherContentValues putPressure(@Nullable Float value) {
        mContentValues.put(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherContentValues putPressureNull() {
        mContentValues.putNull(WeatherColumns.PRESSURE);
        return this;
    }

    public WeatherContentValues putDeg(@Nullable Float value) {
        mContentValues.put(WeatherColumns.DEG, value);
        return this;
    }

    public WeatherContentValues putDegNull() {
        mContentValues.putNull(WeatherColumns.DEG);
        return this;
    }
}
