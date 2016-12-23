package com.codephillip.intmain.e_govt.provider.districts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.codephillip.intmain.e_govt.provider.base.AbstractSelection;

/**
 * Selection for the {@code districts} table.
 */
public class DistrictsSelection extends AbstractSelection<DistrictsSelection> {
    @Override
    protected Uri baseUri() {
        return DistrictsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code DistrictsCursor} object, which is positioned before the first entry, or null.
     */
    public DistrictsCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new DistrictsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public DistrictsCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public DistrictsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public DistrictsSelection id(long... value) {
        addEquals("districts." + DistrictsColumns._ID, toObjectArray(value));
        return this;
    }

    public DistrictsSelection idDistricts(Integer... value) {
        addEquals(DistrictsColumns.ID_DISTRICTS, value);
        return this;
    }

    public DistrictsSelection idDistrictsNot(Integer... value) {
        addNotEquals(DistrictsColumns.ID_DISTRICTS, value);
        return this;
    }

    public DistrictsSelection idDistrictsGt(int value) {
        addGreaterThan(DistrictsColumns.ID_DISTRICTS, value);
        return this;
    }

    public DistrictsSelection idDistrictsGtEq(int value) {
        addGreaterThanOrEquals(DistrictsColumns.ID_DISTRICTS, value);
        return this;
    }

    public DistrictsSelection idDistrictsLt(int value) {
        addLessThan(DistrictsColumns.ID_DISTRICTS, value);
        return this;
    }

    public DistrictsSelection idDistrictsLtEq(int value) {
        addLessThanOrEquals(DistrictsColumns.ID_DISTRICTS, value);
        return this;
    }

    public DistrictsSelection districtName(String... value) {
        addEquals(DistrictsColumns.DISTRICT_NAME, value);
        return this;
    }

    public DistrictsSelection districtNameNot(String... value) {
        addNotEquals(DistrictsColumns.DISTRICT_NAME, value);
        return this;
    }

    public DistrictsSelection districtNameLike(String... value) {
        addLike(DistrictsColumns.DISTRICT_NAME, value);
        return this;
    }

    public DistrictsSelection districtNameContains(String... value) {
        addContains(DistrictsColumns.DISTRICT_NAME, value);
        return this;
    }

    public DistrictsSelection districtNameStartsWith(String... value) {
        addStartsWith(DistrictsColumns.DISTRICT_NAME, value);
        return this;
    }

    public DistrictsSelection districtNameEndsWith(String... value) {
        addEndsWith(DistrictsColumns.DISTRICT_NAME, value);
        return this;
    }

    public DistrictsSelection image(String... value) {
        addEquals(DistrictsColumns.IMAGE, value);
        return this;
    }

    public DistrictsSelection imageNot(String... value) {
        addNotEquals(DistrictsColumns.IMAGE, value);
        return this;
    }

    public DistrictsSelection imageLike(String... value) {
        addLike(DistrictsColumns.IMAGE, value);
        return this;
    }

    public DistrictsSelection imageContains(String... value) {
        addContains(DistrictsColumns.IMAGE, value);
        return this;
    }

    public DistrictsSelection imageStartsWith(String... value) {
        addStartsWith(DistrictsColumns.IMAGE, value);
        return this;
    }

    public DistrictsSelection imageEndsWith(String... value) {
        addEndsWith(DistrictsColumns.IMAGE, value);
        return this;
    }
}
