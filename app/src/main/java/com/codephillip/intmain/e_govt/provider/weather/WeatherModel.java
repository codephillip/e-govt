package com.codephillip.intmain.e_govt.provider.weather;

import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

/**
 * Data model for the {@code weather} table.
 */
public interface WeatherModel extends BaseModel {

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getDate();

    /**
     * Get the {@code date_txt} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDateTxt();

    /**
     * Get the {@code weather_id} value.
     */
    int getWeatherId();

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
     * Get the {@code humidity} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getHumidity();

    /**
     * Get the {@code wind_speed} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getWindSpeed();

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

    /**
     * Get the {@code pressure} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getPressure();

    /**
     * Get the {@code deg} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getDeg();
}
