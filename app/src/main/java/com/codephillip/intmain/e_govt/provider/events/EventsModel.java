package com.codephillip.intmain.e_govt.provider.events;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

import android.support.annotation.Nullable;

/**
 * Data model for the {@code events} table.
 */
public interface EventsModel extends BaseModel {

    /**
     * Get the {@code id_events} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getIdEvents();

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDate();

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * Get the {@code story} value.
     * Can be {@code null}.
     */
    @Nullable
    String getStory();

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImage();

    /**
     * Get the {@code ministry} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMinistry();

    /**
     * Get the {@code location} value.
     * Can be {@code null}.
     */
    @Nullable
    String getLocation();
}
