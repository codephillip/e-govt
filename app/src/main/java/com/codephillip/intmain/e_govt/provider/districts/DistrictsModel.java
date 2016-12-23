package com.codephillip.intmain.e_govt.provider.districts;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

import android.support.annotation.Nullable;

/**
 * Data model for the {@code districts} table.
 */
public interface DistrictsModel extends BaseModel {

    /**
     * Get the {@code id_districts} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getIdDistricts();

    /**
     * Get the {@code district_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDistrictName();

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImage();
}
