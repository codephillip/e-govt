package com.codephillip.intmain.e_govt.provider.chapters;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
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
        Integer res = getIntegerOrNull(ChaptersColumns.ID_CHAPTERS);
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDate() {
        String res = getStringOrNull(ChaptersColumns.DATE);
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(ChaptersColumns.TITLE);
        return res;
    }

    /**
     * Get the {@code story} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getStory() {
        String res = getStringOrNull(ChaptersColumns.STORY);
        return res;
    }

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImage() {
        String res = getStringOrNull(ChaptersColumns.IMAGE);
        return res;
    }

    /**
     * Get the {@code ministry} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMinistry() {
        String res = getStringOrNull(ChaptersColumns.MINISTRY);
        return res;
    }

    /**
     * Get the {@code district} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDistrict() {
        String res = getStringOrNull(ChaptersColumns.DISTRICT);
        return res;
    }
}
