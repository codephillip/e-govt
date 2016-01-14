package com.codephillip.intmain.e_govt.provider.ministries;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.codephillip.intmain.e_govt.provider.base.AbstractSelection;

/**
 * Selection for the {@code ministries} table.
 */
public class MinistriesSelection extends AbstractSelection<MinistriesSelection> {
    @Override
    protected Uri baseUri() {
        return MinistriesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code MinistriesCursor} object, which is positioned before the first entry, or null.
     */
    public MinistriesCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new MinistriesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public MinistriesCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public MinistriesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public MinistriesSelection id(long... value) {
        addEquals("ministries." + MinistriesColumns._ID, toObjectArray(value));
        return this;
    }

    public MinistriesSelection idMinistries(Integer... value) {
        addEquals(MinistriesColumns.ID_MINISTRIES, value);
        return this;
    }

    public MinistriesSelection idMinistriesNot(Integer... value) {
        addNotEquals(MinistriesColumns.ID_MINISTRIES, value);
        return this;
    }

    public MinistriesSelection idMinistriesGt(int value) {
        addGreaterThan(MinistriesColumns.ID_MINISTRIES, value);
        return this;
    }

    public MinistriesSelection idMinistriesGtEq(int value) {
        addGreaterThanOrEquals(MinistriesColumns.ID_MINISTRIES, value);
        return this;
    }

    public MinistriesSelection idMinistriesLt(int value) {
        addLessThan(MinistriesColumns.ID_MINISTRIES, value);
        return this;
    }

    public MinistriesSelection idMinistriesLtEq(int value) {
        addLessThanOrEquals(MinistriesColumns.ID_MINISTRIES, value);
        return this;
    }

    public MinistriesSelection ministryName(String... value) {
        addEquals(MinistriesColumns.MINISTRY_NAME, value);
        return this;
    }

    public MinistriesSelection ministryNameNot(String... value) {
        addNotEquals(MinistriesColumns.MINISTRY_NAME, value);
        return this;
    }

    public MinistriesSelection ministryNameLike(String... value) {
        addLike(MinistriesColumns.MINISTRY_NAME, value);
        return this;
    }

    public MinistriesSelection ministryNameContains(String... value) {
        addContains(MinistriesColumns.MINISTRY_NAME, value);
        return this;
    }

    public MinistriesSelection ministryNameStartsWith(String... value) {
        addStartsWith(MinistriesColumns.MINISTRY_NAME, value);
        return this;
    }

    public MinistriesSelection ministryNameEndsWith(String... value) {
        addEndsWith(MinistriesColumns.MINISTRY_NAME, value);
        return this;
    }

    public MinistriesSelection image(String... value) {
        addEquals(MinistriesColumns.IMAGE, value);
        return this;
    }

    public MinistriesSelection imageNot(String... value) {
        addNotEquals(MinistriesColumns.IMAGE, value);
        return this;
    }

    public MinistriesSelection imageLike(String... value) {
        addLike(MinistriesColumns.IMAGE, value);
        return this;
    }

    public MinistriesSelection imageContains(String... value) {
        addContains(MinistriesColumns.IMAGE, value);
        return this;
    }

    public MinistriesSelection imageStartsWith(String... value) {
        addStartsWith(MinistriesColumns.IMAGE, value);
        return this;
    }

    public MinistriesSelection imageEndsWith(String... value) {
        addEndsWith(MinistriesColumns.IMAGE, value);
        return this;
    }
}
