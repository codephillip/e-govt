package com.codephillip.intmain.e_govt.provider.districts;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code districts} table.
 */
public class DistrictsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return DistrictsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable DistrictsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public DistrictsContentValues putIdDistricts(@Nullable Integer value) {
        mContentValues.put(DistrictsColumns.ID_DISTRICTS, value);
        return this;
    }

    public DistrictsContentValues putIdDistrictsNull() {
        mContentValues.putNull(DistrictsColumns.ID_DISTRICTS);
        return this;
    }

    public DistrictsContentValues putDistrictName(@Nullable String value) {
        mContentValues.put(DistrictsColumns.DISTRICT_NAME, value);
        return this;
    }

    public DistrictsContentValues putDistrictNameNull() {
        mContentValues.putNull(DistrictsColumns.DISTRICT_NAME);
        return this;
    }

    public DistrictsContentValues putImage(@Nullable String value) {
        mContentValues.put(DistrictsColumns.IMAGE, value);
        return this;
    }

    public DistrictsContentValues putImageNull() {
        mContentValues.putNull(DistrictsColumns.IMAGE);
        return this;
    }
}
