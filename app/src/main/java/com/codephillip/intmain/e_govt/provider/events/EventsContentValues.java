package com.codephillip.intmain.e_govt.provider.events;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.codephillip.intmain.e_govt.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code events} table.
 */
public class EventsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return EventsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable EventsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public EventsContentValues putIdEvents(@Nullable Integer value) {
        mContentValues.put(EventsColumns.ID_EVENTS, value);
        return this;
    }

    public EventsContentValues putIdEventsNull() {
        mContentValues.putNull(EventsColumns.ID_EVENTS);
        return this;
    }

    public EventsContentValues putDate(@Nullable String value) {
        mContentValues.put(EventsColumns.DATE, value);
        return this;
    }

    public EventsContentValues putDateNull() {
        mContentValues.putNull(EventsColumns.DATE);
        return this;
    }

    public EventsContentValues putTitle(@Nullable String value) {
        mContentValues.put(EventsColumns.TITLE, value);
        return this;
    }

    public EventsContentValues putTitleNull() {
        mContentValues.putNull(EventsColumns.TITLE);
        return this;
    }

    public EventsContentValues putStory(@Nullable String value) {
        mContentValues.put(EventsColumns.STORY, value);
        return this;
    }

    public EventsContentValues putStoryNull() {
        mContentValues.putNull(EventsColumns.STORY);
        return this;
    }

    public EventsContentValues putImage(@Nullable String value) {
        mContentValues.put(EventsColumns.IMAGE, value);
        return this;
    }

    public EventsContentValues putImageNull() {
        mContentValues.putNull(EventsColumns.IMAGE);
        return this;
    }

    public EventsContentValues putMinistry(@Nullable String value) {
        mContentValues.put(EventsColumns.MINISTRY, value);
        return this;
    }

    public EventsContentValues putMinistryNull() {
        mContentValues.putNull(EventsColumns.MINISTRY);
        return this;
    }

    public EventsContentValues putLocation(@Nullable String value) {
        mContentValues.put(EventsColumns.LOCATION, value);
        return this;
    }

    public EventsContentValues putLocationNull() {
        mContentValues.putNull(EventsColumns.LOCATION);
        return this;
    }
}
