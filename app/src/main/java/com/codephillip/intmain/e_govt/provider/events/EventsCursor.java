package com.codephillip.intmain.e_govt.provider.events;

import android.database.Cursor;
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
        Integer res = getIntegerOrNull(EventsColumns.ID_EVENTS);
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDate() {
        String res = getStringOrNull(EventsColumns.DATE);
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(EventsColumns.TITLE);
        return res;
    }

    /**
     * Get the {@code story} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getStory() {
        String res = getStringOrNull(EventsColumns.STORY);
        return res;
    }

    /**
     * Get the {@code image} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImage() {
        String res = getStringOrNull(EventsColumns.IMAGE);
        return res;
    }

    /**
     * Get the {@code ministry} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMinistry() {
        String res = getStringOrNull(EventsColumns.MINISTRY);
        return res;
    }

    /**
     * Get the {@code location} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLocation() {
        String res = getStringOrNull(EventsColumns.LOCATION);
        return res;
    }
}
