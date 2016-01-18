package com.codephillip.intmain.e_govt.provider.todayweather;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code todayweather} table.
 */
public interface TodayweatherModel extends BaseModel {

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getDate();

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Get the {@code main} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMain();

    /**
     * Get the {@code max_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getMaxTemp();

    /**
     * Get the {@code min_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getMinTemp();
}
