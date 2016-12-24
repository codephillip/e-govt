package com.codephillip.intmain.e_govt.provider.todayweather;

import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

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
     * Get the {@code weather_id} value.
     */
    int getWeatherId();

    /**
     * Get the {@code city_id} value.
     */
    int getCityId();

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
    Double getMaxTemp();

    /**
     * Get the {@code min_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getMinTemp();
}
