package com.codephillip.intmain.e_govt.provider.chapters;

import com.codephillip.intmain.e_govt.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code chapters} table.
 */
public interface ChaptersModel extends BaseModel {

    /**
     * Get the {@code id_chapters} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getIdChapters();

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
     * Get the {@code district} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDistrict();
}
