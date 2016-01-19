package com.codephillip.intmain.e_govt.provider.ministries;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code ministries} table.
 */
public class MinistriesCursor extends AbstractCursor implements MinistriesModel {
    public MinistriesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MinistriesColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code id_ministries} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getIdMinistries() {
        Integer res = getIntegerOrNull(MinistriesColumns.ID_MINISTRIES);
        return res;
    }

    /**
     * Get the {@code ministry_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMinistryName() {
        String res = getStringOrNull(MinistriesColumns.MINISTRY_NAME);
        return res;
    }

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImage() {
        String res = getStringOrNull(MinistriesColumns.IMAGE);
        return res;
    }
}
