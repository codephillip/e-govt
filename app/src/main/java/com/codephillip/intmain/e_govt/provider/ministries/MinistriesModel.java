package com.codephillip.intmain.e_govt.provider.ministries;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
