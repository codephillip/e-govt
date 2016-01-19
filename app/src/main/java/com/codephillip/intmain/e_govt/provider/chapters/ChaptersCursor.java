package com.codephillip.intmain.e_govt.provider.chapters;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code chapters} table.
 */
public class ChaptersCursor extends AbstractCursor implements ChaptersModel {
    public ChaptersCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ChaptersColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code id_chapters} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getIdChapters() {
        return getIntegerOrNull(ChaptersColumns.ID_CHAPTERS);
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDate() {
        return getStringOrNull(ChaptersColumns.DATE);
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        return getStringOrNull(ChaptersColumns.TITLE);
    }

    /**
     * Get the {@code story} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getStory() {
        return getStringOrNull(ChaptersColumns.STORY);
    }

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImage() {
        return getStringOrNull(ChaptersColumns.IMAGE);
    }

    /**
     * Get the {@code ministry} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMinistry() {
        return getStringOrNull(ChaptersColumns.MINISTRY);
    }

    /**
     * Get the {@code district} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDistrict() {
        return getStringOrNull(ChaptersColumns.DISTRICT);
    }
}
