package com.codephillip.intmain.e_govt.provider.ministries;

import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

/**
 * Data model for the {@code ministries} table.
 */
public interface MinistriesModel extends BaseModel {

    /**
     * Get the {@code id_ministries} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getIdMinistries();

    /**
     * Get the {@code ministry_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMinistryName();

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImage();
}
