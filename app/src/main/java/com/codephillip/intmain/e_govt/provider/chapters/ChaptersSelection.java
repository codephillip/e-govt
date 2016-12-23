package com.codephillip.intmain.e_govt.provider.chapters;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.codephillip.intmain.e_govt.provider.base.AbstractSelection;

/**
 * Selection for the {@code chapters} table.
 */
public class ChaptersSelection extends AbstractSelection<ChaptersSelection> {
    @Override
    protected Uri baseUri() {
        return ChaptersColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ChaptersCursor} object, which is positioned before the first entry, or null.
     */
    public ChaptersCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ChaptersCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public ChaptersCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public ChaptersCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public ChaptersSelection id(long... value) {
        addEquals("chapters." + ChaptersColumns._ID, toObjectArray(value));
        return this;
    }

    public ChaptersSelection idChapters(Integer... value) {
        addEquals(ChaptersColumns.ID_CHAPTERS, value);
        return this;
    }

    public ChaptersSelection idChaptersNot(Integer... value) {
        addNotEquals(ChaptersColumns.ID_CHAPTERS, value);
        return this;
    }

    public ChaptersSelection idChaptersGt(int value) {
        addGreaterThan(ChaptersColumns.ID_CHAPTERS, value);
        return this;
    }

    public ChaptersSelection idChaptersGtEq(int value) {
        addGreaterThanOrEquals(ChaptersColumns.ID_CHAPTERS, value);
        return this;
    }

    public ChaptersSelection idChaptersLt(int value) {
        addLessThan(ChaptersColumns.ID_CHAPTERS, value);
        return this;
    }

    public ChaptersSelection idChaptersLtEq(int value) {
        addLessThanOrEquals(ChaptersColumns.ID_CHAPTERS, value);
        return this;
    }

    public ChaptersSelection date(String... value) {
        addEquals(ChaptersColumns.DATE, value);
        return this;
    }

    public ChaptersSelection dateNot(String... value) {
        addNotEquals(ChaptersColumns.DATE, value);
        return this;
    }

    public ChaptersSelection dateLike(String... value) {
        addLike(ChaptersColumns.DATE, value);
        return this;
    }

    public ChaptersSelection dateContains(String... value) {
        addContains(ChaptersColumns.DATE, value);
        return this;
    }

    public ChaptersSelection dateStartsWith(String... value) {
        addStartsWith(ChaptersColumns.DATE, value);
        return this;
    }

    public ChaptersSelection dateEndsWith(String... value) {
        addEndsWith(ChaptersColumns.DATE, value);
        return this;
    }

    public ChaptersSelection title(String... value) {
        addEquals(ChaptersColumns.TITLE, value);
        return this;
    }

    public ChaptersSelection titleNot(String... value) {
        addNotEquals(ChaptersColumns.TITLE, value);
        return this;
    }

    public ChaptersSelection titleLike(String... value) {
        addLike(ChaptersColumns.TITLE, value);
        return this;
    }

    public ChaptersSelection titleContains(String... value) {
        addContains(ChaptersColumns.TITLE, value);
        return this;
    }

    public ChaptersSelection titleStartsWith(String... value) {
        addStartsWith(ChaptersColumns.TITLE, value);
        return this;
    }

    public ChaptersSelection titleEndsWith(String... value) {
        addEndsWith(ChaptersColumns.TITLE, value);
        return this;
    }

    public ChaptersSelection story(String... value) {
        addEquals(ChaptersColumns.STORY, value);
        return this;
    }

    public ChaptersSelection storyNot(String... value) {
        addNotEquals(ChaptersColumns.STORY, value);
        return this;
    }

    public ChaptersSelection storyLike(String... value) {
        addLike(ChaptersColumns.STORY, value);
        return this;
    }

    public ChaptersSelection storyContains(String... value) {
        addContains(ChaptersColumns.STORY, value);
        return this;
    }

    public ChaptersSelection storyStartsWith(String... value) {
        addStartsWith(ChaptersColumns.STORY, value);
        return this;
    }

    public ChaptersSelection storyEndsWith(String... value) {
        addEndsWith(ChaptersColumns.STORY, value);
        return this;
    }

    public ChaptersSelection image(String... value) {
        addEquals(ChaptersColumns.IMAGE, value);
        return this;
    }

    public ChaptersSelection imageNot(String... value) {
        addNotEquals(ChaptersColumns.IMAGE, value);
        return this;
    }

    public ChaptersSelection imageLike(String... value) {
        addLike(ChaptersColumns.IMAGE, value);
        return this;
    }

    public ChaptersSelection imageContains(String... value) {
        addContains(ChaptersColumns.IMAGE, value);
        return this;
    }

    public ChaptersSelection imageStartsWith(String... value) {
        addStartsWith(ChaptersColumns.IMAGE, value);
        return this;
    }

    public ChaptersSelection imageEndsWith(String... value) {
        addEndsWith(ChaptersColumns.IMAGE, value);
        return this;
    }

    public ChaptersSelection ministry(String... value) {
        addEquals(ChaptersColumns.MINISTRY, value);
        return this;
    }

    public ChaptersSelection ministryNot(String... value) {
        addNotEquals(ChaptersColumns.MINISTRY, value);
        return this;
    }

    public ChaptersSelection ministryLike(String... value) {
        addLike(ChaptersColumns.MINISTRY, value);
        return this;
    }

    public ChaptersSelection ministryContains(String... value) {
        addContains(ChaptersColumns.MINISTRY, value);
        return this;
    }

    public ChaptersSelection ministryStartsWith(String... value) {
        addStartsWith(ChaptersColumns.MINISTRY, value);
        return this;
    }

    public ChaptersSelection ministryEndsWith(String... value) {
        addEndsWith(ChaptersColumns.MINISTRY, value);
        return this;
    }

    public ChaptersSelection district(String... value) {
        addEquals(ChaptersColumns.DISTRICT, value);
        return this;
    }

    public ChaptersSelection districtNot(String... value) {
        addNotEquals(ChaptersColumns.DISTRICT, value);
        return this;
    }

    public ChaptersSelection districtLike(String... value) {
        addLike(ChaptersColumns.DISTRICT, value);
        return this;
    }

    public ChaptersSelection districtContains(String... value) {
        addContains(ChaptersColumns.DISTRICT, value);
        return this;
    }

    public ChaptersSelection districtStartsWith(String... value) {
        addStartsWith(ChaptersColumns.DISTRICT, value);
        return this;
    }

    public ChaptersSelection districtEndsWith(String... value) {
        addEndsWith(ChaptersColumns.DISTRICT, value);
        return this;
    }
}
