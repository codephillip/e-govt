package com.codephillip.intmain.e_govt.provider.ministries;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code ministries} table.
 */
public class MinistriesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MinistriesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MinistriesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MinistriesContentValues putIdMinistries(@Nullable Integer value) {
        mContentValues.put(MinistriesColumns.ID_MINISTRIES, value);
        return this;
    }

    public MinistriesContentValues putIdMinistriesNull() {
        mContentValues.putNull(MinistriesColumns.ID_MINISTRIES);
        return this;
    }

    public MinistriesContentValues putMinistryName(@Nullable String value) {
        mContentValues.put(MinistriesColumns.MINISTRY_NAME, value);
        return this;
    }

    public MinistriesContentValues putMinistryNameNull() {
        mContentValues.putNull(MinistriesColumns.MINISTRY_NAME);
        return this;
    }

    public MinistriesContentValues putImage(@Nullable String value) {
        mContentValues.put(MinistriesColumns.IMAGE, value);
        return this;
    }

    public MinistriesContentValues putImageNull() {
        mContentValues.putNull(MinistriesColumns.IMAGE);
        return this;
    }
}
