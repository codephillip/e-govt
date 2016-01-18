package com.codephillip.intmain.e_govt.provider.chapters;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code chapters} table.
 */
public class ChaptersContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ChaptersColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ChaptersSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ChaptersContentValues putIdChapters(@Nullable Integer value) {
        mContentValues.put(ChaptersColumns.ID_CHAPTERS, value);
        return this;
    }

    public ChaptersContentValues putIdChaptersNull() {
        mContentValues.putNull(ChaptersColumns.ID_CHAPTERS);
        return this;
    }

    public ChaptersContentValues putDate(@Nullable String value) {
        mContentValues.put(ChaptersColumns.DATE, value);
        return this;
    }

    public ChaptersContentValues putDateNull() {
        mContentValues.putNull(ChaptersColumns.DATE);
        return this;
    }

    public ChaptersContentValues putTitle(@Nullable String value) {
        mContentValues.put(ChaptersColumns.TITLE, value);
        return this;
    }

    public ChaptersContentValues putTitleNull() {
        mContentValues.putNull(ChaptersColumns.TITLE);
        return this;
    }

    public ChaptersContentValues putStory(@Nullable String value) {
        mContentValues.put(ChaptersColumns.STORY, value);
        return this;
    }

    public ChaptersContentValues putStoryNull() {
        mContentValues.putNull(ChaptersColumns.STORY);
        return this;
    }

    public ChaptersContentValues putImage(@Nullable String value) {
        mContentValues.put(ChaptersColumns.IMAGE, value);
        return this;
    }

    public ChaptersContentValues putImageNull() {
        mContentValues.putNull(ChaptersColumns.IMAGE);
        return this;
    }

    public ChaptersContentValues putMinistry(@Nullable String value) {
        mContentValues.put(ChaptersColumns.MINISTRY, value);
        return this;
    }

    public ChaptersContentValues putMinistryNull() {
        mContentValues.putNull(ChaptersColumns.MINISTRY);
        return this;
    }

    public ChaptersContentValues putDistrict(@Nullable String value) {
        mContentValues.put(ChaptersColumns.DISTRICT, value);
        return this;
    }

    public ChaptersContentValues putDistrictNull() {
        mContentValues.putNull(ChaptersColumns.DISTRICT);
        return this;
    }
}
