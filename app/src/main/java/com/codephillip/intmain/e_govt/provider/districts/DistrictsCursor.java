package com.codephillip.intmain.e_govt.provider.districts;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code districts} table.
 */
public class DistrictsCursor extends AbstractCursor implements DistrictsModel {
    public DistrictsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(DistrictsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code id_districts} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getIdDistricts() {
        Integer res = getIntegerOrNull(DistrictsColumns.ID_DISTRICTS);
        return res;
    }

    /**
     * Get the {@code district_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDistrictName() {
        String res = getStringOrNull(DistrictsColumns.DISTRICT_NAME);
        return res;
    }

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImage() {
        String res = getStringOrNull(DistrictsColumns.IMAGE);
        return res;
    }
}
