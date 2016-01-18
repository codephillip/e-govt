package com.codephillip.intmain.e_govt.provider.weather;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.codephillip.intmain.e_govt.provider.base.AbstractSelection;

/**
 * Selection for the {@code weather} table.
 */
public class WeatherSelection extends AbstractSelection<WeatherSelection> {
    @Override
    protected Uri baseUri() {
        return WeatherColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code WeatherCursor} object, which is positioned before the first entry, or null.
     */
    public WeatherCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new WeatherCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public WeatherCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public WeatherCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public WeatherSelection id(long... value) {
        addEquals("weather." + WeatherColumns._ID, toObjectArray(value));
        return this;
    }

    public WeatherSelection date(Integer... value) {
        addEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateNot(Integer... value) {
        addNotEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateGt(int value) {
        addGreaterThan(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateGtEq(int value) {
        addGreaterThanOrEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateLt(int value) {
        addLessThan(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateLtEq(int value) {
        addLessThanOrEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection name(String... value) {
        addEquals(WeatherColumns.NAME, value);
        return this;
    }

    public WeatherSelection nameNot(String... value) {
        addNotEquals(WeatherColumns.NAME, value);
        return this;
    }

    public WeatherSelection nameLike(String... value) {
        addLike(WeatherColumns.NAME, value);
        return this;
    }

    public WeatherSelection nameContains(String... value) {
        addContains(WeatherColumns.NAME, value);
        return this;
    }

    public WeatherSelection nameStartsWith(String... value) {
        addStartsWith(WeatherColumns.NAME, value);
        return this;
    }

    public WeatherSelection nameEndsWith(String... value) {
        addEndsWith(WeatherColumns.NAME, value);
        return this;
    }

    public WeatherSelection main(String... value) {
        addEquals(WeatherColumns.MAIN, value);
        return this;
    }

    public WeatherSelection mainNot(String... value) {
        addNotEquals(WeatherColumns.MAIN, value);
        return this;
    }

    public WeatherSelection mainLike(String... value) {
        addLike(WeatherColumns.MAIN, value);
        return this;
    }

    public WeatherSelection mainContains(String... value) {
        addContains(WeatherColumns.MAIN, value);
        return this;
    }

    public WeatherSelection mainStartsWith(String... value) {
        addStartsWith(WeatherColumns.MAIN, value);
        return this;
    }

    public WeatherSelection mainEndsWith(String... value) {
        addEndsWith(WeatherColumns.MAIN, value);
        return this;
    }

    public WeatherSelection humidity(Float... value) {
        addEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityNot(Float... value) {
        addNotEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityGt(float value) {
        addGreaterThan(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityLt(float value) {
        addLessThan(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection windSpeed(Float... value) {
        addEquals(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedNot(Float... value) {
        addNotEquals(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedGt(float value) {
        addGreaterThan(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedLt(float value) {
        addLessThan(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection maxTemp(Float... value) {
        addEquals(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempNot(Float... value) {
        addNotEquals(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempGt(float value) {
        addGreaterThan(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempLt(float value) {
        addLessThan(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection minTemp(Float... value) {
        addEquals(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempNot(Float... value) {
        addNotEquals(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempGt(float value) {
        addGreaterThan(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempLt(float value) {
        addLessThan(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection pressure(Float... value) {
        addEquals(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureNot(Float... value) {
        addNotEquals(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureGt(float value) {
        addGreaterThan(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureLt(float value) {
        addLessThan(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection deg(Float... value) {
        addEquals(WeatherColumns.DEG, value);
        return this;
    }

    public WeatherSelection degNot(Float... value) {
        addNotEquals(WeatherColumns.DEG, value);
        return this;
    }

    public WeatherSelection degGt(float value) {
        addGreaterThan(WeatherColumns.DEG, value);
        return this;
    }

    public WeatherSelection degGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.DEG, value);
        return this;
    }

    public WeatherSelection degLt(float value) {
        addLessThan(WeatherColumns.DEG, value);
        return this;
    }

    public WeatherSelection degLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.DEG, value);
        return this;
    }
}
