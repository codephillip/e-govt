package com.codephillip.intmain.e_govt.provider.weather;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

import android.support.annotation.Nullable;

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
    Float getHumidity();

    /**
     * Get the {@code wind_speed} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getWindSpeed();

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

    /**
     * Get the {@code pressure} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getPressure();

    /**
     * Get the {@code deg} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getDeg();
}
