package com.codephillip.intmain.e_govt.provider.events;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code events} table.
 */
public class EventsCursor extends AbstractCursor implements EventsModel {
    public EventsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(EventsColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code id_events} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getIdEvents() {
        return getIntegerOrNull(EventsColumns.ID_EVENTS);
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDate() {
        return getStringOrNull(EventsColumns.DATE);
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        return getStringOrNull(EventsColumns.TITLE);
    }

    /**
     * Get the {@code story} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getStory() {
        return getStringOrNull(EventsColumns.STORY);
    }

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImage() {
        return getStringOrNull(EventsColumns.IMAGE);
    }

    /**
     * Get the {@code ministry} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMinistry() {
        return getStringOrNull(EventsColumns.MINISTRY);
    }

    /**
     * Get the {@code location} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLocation() {
        return getStringOrNull(EventsColumns.LOCATION);
    }
}
