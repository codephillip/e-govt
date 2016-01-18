package com.codephillip.intmain.e_govt.provider.todayweather;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.codephillip.intmain.e_govt.provider.base.AbstractSelection;

/**
 * Selection for the {@code todayweather} table.
 */
public class TodayweatherSelection extends AbstractSelection<TodayweatherSelection> {
    @Override
    protected Uri baseUri() {
        return TodayweatherColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code TodayweatherCursor} object, which is positioned before the first entry, or null.
     */
    public TodayweatherCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new TodayweatherCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public TodayweatherCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public TodayweatherCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public TodayweatherSelection id(long... value) {
        addEquals("todayweather." + TodayweatherColumns._ID, toObjectArray(value));
        return this;
    }

    public TodayweatherSelection date(Integer... value) {
        addEquals(TodayweatherColumns.DATE, value);
        return this;
    }

    public TodayweatherSelection dateNot(Integer... value) {
        addNotEquals(TodayweatherColumns.DATE, value);
        return this;
    }

    public TodayweatherSelection dateGt(int value) {
        addGreaterThan(TodayweatherColumns.DATE, value);
        return this;
    }

    public TodayweatherSelection dateGtEq(int value) {
        addGreaterThanOrEquals(TodayweatherColumns.DATE, value);
        return this;
    }

    public TodayweatherSelection dateLt(int value) {
        addLessThan(TodayweatherColumns.DATE, value);
        return this;
    }

    public TodayweatherSelection dateLtEq(int value) {
        addLessThanOrEquals(TodayweatherColumns.DATE, value);
        return this;
    }

    public TodayweatherSelection weatherId(int... value) {
        addEquals(TodayweatherColumns.WEATHER_ID, toObjectArray(value));
        return this;
    }

    public TodayweatherSelection weatherIdNot(int... value) {
        addNotEquals(TodayweatherColumns.WEATHER_ID, toObjectArray(value));
        return this;
    }

    public TodayweatherSelection weatherIdGt(int value) {
        addGreaterThan(TodayweatherColumns.WEATHER_ID, value);
        return this;
    }

    public TodayweatherSelection weatherIdGtEq(int value) {
        addGreaterThanOrEquals(TodayweatherColumns.WEATHER_ID, value);
        return this;
    }

    public TodayweatherSelection weatherIdLt(int value) {
        addLessThan(TodayweatherColumns.WEATHER_ID, value);
        return this;
    }

    public TodayweatherSelection weatherIdLtEq(int value) {
        addLessThanOrEquals(TodayweatherColumns.WEATHER_ID, value);
        return this;
    }

    public TodayweatherSelection name(String... value) {
        addEquals(TodayweatherColumns.NAME, value);
        return this;
    }

    public TodayweatherSelection nameNot(String... value) {
        addNotEquals(TodayweatherColumns.NAME, value);
        return this;
    }

    public TodayweatherSelection nameLike(String... value) {
        addLike(TodayweatherColumns.NAME, value);
        return this;
    }

    public TodayweatherSelection nameContains(String... value) {
        addContains(TodayweatherColumns.NAME, value);
        return this;
    }

    public TodayweatherSelection nameStartsWith(String... value) {
        addStartsWith(TodayweatherColumns.NAME, value);
        return this;
    }

    public TodayweatherSelection nameEndsWith(String... value) {
        addEndsWith(TodayweatherColumns.NAME, value);
        return this;
    }

    public TodayweatherSelection main(String... value) {
        addEquals(TodayweatherColumns.MAIN, value);
        return this;
    }

    public TodayweatherSelection mainNot(String... value) {
        addNotEquals(TodayweatherColumns.MAIN, value);
        return this;
    }

    public TodayweatherSelection mainLike(String... value) {
        addLike(TodayweatherColumns.MAIN, value);
        return this;
    }

    public TodayweatherSelection mainContains(String... value) {
        addContains(TodayweatherColumns.MAIN, value);
        return this;
    }

    public TodayweatherSelection mainStartsWith(String... value) {
        addStartsWith(TodayweatherColumns.MAIN, value);
        return this;
    }

    public TodayweatherSelection mainEndsWith(String... value) {
        addEndsWith(TodayweatherColumns.MAIN, value);
        return this;
    }

    public TodayweatherSelection maxTemp(Float... value) {
        addEquals(TodayweatherColumns.MAX_TEMP, value);
        return this;
    }

    public TodayweatherSelection maxTempNot(Float... value) {
        addNotEquals(TodayweatherColumns.MAX_TEMP, value);
        return this;
    }

    public TodayweatherSelection maxTempGt(float value) {
        addGreaterThan(TodayweatherColumns.MAX_TEMP, value);
        return this;
    }

    public TodayweatherSelection maxTempGtEq(float value) {
        addGreaterThanOrEquals(TodayweatherColumns.MAX_TEMP, value);
        return this;
    }

    public TodayweatherSelection maxTempLt(float value) {
        addLessThan(TodayweatherColumns.MAX_TEMP, value);
        return this;
    }

    public TodayweatherSelection maxTempLtEq(float value) {
        addLessThanOrEquals(TodayweatherColumns.MAX_TEMP, value);
        return this;
    }

    public TodayweatherSelection minTemp(Float... value) {
        addEquals(TodayweatherColumns.MIN_TEMP, value);
        return this;
    }

    public TodayweatherSelection minTempNot(Float... value) {
        addNotEquals(TodayweatherColumns.MIN_TEMP, value);
        return this;
    }

    public TodayweatherSelection minTempGt(float value) {
        addGreaterThan(TodayweatherColumns.MIN_TEMP, value);
        return this;
    }

    public TodayweatherSelection minTempGtEq(float value) {
        addGreaterThanOrEquals(TodayweatherColumns.MIN_TEMP, value);
        return this;
    }

    public TodayweatherSelection minTempLt(float value) {
        addLessThan(TodayweatherColumns.MIN_TEMP, value);
        return this;
    }

    public TodayweatherSelection minTempLtEq(float value) {
        addLessThanOrEquals(TodayweatherColumns.MIN_TEMP, value);
        return this;
    }
}
